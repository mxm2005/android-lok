<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����Ԥ�� ����</title>
   
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
</head>
<body>
    <span id="weather" style="color:#4c4c4c;"></span>
</body>
</html>
<script type="text/javascript">
    //��̬����js 
    function loadJs(jsUrl, fCallBack) {
        var _script = document.createElement('script');
        _script.setAttribute('type', 'text/javascript');
        _script.setAttribute('src', jsUrl);
        document.getElementsByTagName("head")[0].appendChild(_script);
        if (typeof fCallBack != 'undefined') {
            //if ($.browser.msie) //����jQuery�ж�ie 
            if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
                _script.onreadystatechange = function () {
                    if (this.readyState == 'loaded' || this.readyState == 'complete') {
                        fCallBack();
                    }
                };
            } else {
                _script.onreadystatechange = function () {
                    fCallBack();
                };
            }
        }
    }
    var cityIdUrl = 'http://61.4.185.48:81/g/';
    function getWeather(f_cb) {
        loadJs(cityIdUrl, function () {
            if (typeof id != 'undefined') {
                var curDayWeather; //�����ڴ���ӻ������ 
                //���ݲ�ͬ���е�id��ȡ��������Ԥ�� 
                var URL = "http://m.weather.com.cn/data/" + id + ".html"; //���ݳ���id�õ�����ת��·��
                $.getJSON(URL, function (data) { //����·�� ��Խ��ȡ����Ԥ��
                    var w_info = data.weatherinfo;
                    //������Ծ���ؽ�����ȡ������������,���Ҫ��ȡ�������δ�������ݿ����ڴ˽�����չ 
                    //json���� 
                    f_cb(w_info.date_y + "|" + w_info.week + "|" + w_info.city + "|" + w_info.temp1 + "|" + w_info.weather1 + "|" + w_info.wind1 + "|" + w_info.tempF1);
                });
            }
        });
    }

	
    //���� ����Ҫ���ı�ʾ���� ���÷������ǿ�
    getWeather var(function (weather_data) {
        //������õ���weather_data�ŵ�����Ҫ��λ�á� 
       return weather_date;
    }); 
</script>