import flask
import io
import string
import time
import os
import numpy as np
import tensorflow as tf
from PIL import Image
from flask import Flask, jsonify, request 

model = tf.keras.models.load_model('gs://sibi-translator-bucket/B21-CAP0306-main/Machine Learning/model.tflite')

def img_prep(img):
    img = Image.open(io.BytesIO(img))
    img = img.resize((224, 224))
    img = np.array(img)
    img = np.expand_dims(img, 0)
    return img


def pred_res(img):
    return 1 if model.predict(img)[0][0] > 0.5 else 0

app = Flask(__name__)

@app.route('/predict', methods=['POST'])
def infer_img():
    # Catch the image file from a POST request
    if 'file' not in request.files:
        return "Please try again. an Image doesn't exist"
    
    file = request.files.get('file')

    if not file:
        return

    # Read the image
    img_bytes = file.read()

    # Prepare the image
    img = img_prep(img_bytes)

    # Return on a JSON format
    return jsonify(prediction=pred_res(img))
    

@app.route('/', methods=['GET'])
def index():
    return 'Machine Learning Inference'

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')