import random

from flask import Flask, request, jsonify
import execjs    
import urllib.parse

app = Flask(__name__)


def generate_random_str(self, randomlength=107):
    """
    根据传入长度产生随机字符串
    """
    random_str = ''
    base_str = 'ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789='
    length = len(base_str) - 1
    for _ in range(randomlength):
        random_str += base_str[random.randint(0, length)]
    return random_str
@app.route('/X-Bogus', methods=['POST'])
def generate_request_params():
    data = request.get_json()
    url = data.get('url')
    user_agent = data.get('user_agent')
    query = urllib.parse.urlparse(url).query
    xbogus = execjs.compile(open('./X-Bogus.js').read()).call('sign', query, user_agent)
    new_url = url + "&X-Bogus=" + xbogus
    msToken = generate_random_str(107)
    response_data = {
        "param": new_url,
        "X-Bogus": xbogus,
        "msToken": msToken
    }
    return jsonify(response_data)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8787)
