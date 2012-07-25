<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>天气预报 测试</title>
   
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
</head>
<body>
    <span id="weather" style="color:#4c4c4c;"></span>
</body>
</html>
<script type="text/javascript">
    //动态加载js 
    function loadJs(jsUrl, fCallBack) {
        var _script = document.createElement('script');
        _script.setAttribute('type', 'text/javascript');
        _script.setAttribute('src', jsUrl);
        document.getElementsByTagName("head")[0].appendChild(_script);
        if (typeof fCallBack != 'undefined') {
            //if ($.browser.msie) //借助jQuery判断ie 
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
                var curDayWeather; //可以在此添加缓存机制 
                //根据不同城市的id获取它的天气预报 
                var URL = "http://m.weather.com.cn/data/" + id + ".html"; //根据城市id得到天气转接路径
                $.getJSON(URL, function (data) { //根据路径 跨越获取天气预报
                    var w_info = data.weatherinfo;
                    //下面可以尽情地解析获取到的天气数据,如果要获取近几天和未来的数据可以在此进行扩展 
                    //json对象 
                    f_cb(w_info.date_y + "|" + w_info.week + "|" + w_info.city + "|" + w_info.temp1 + "|" + w_info.weather1 + "|" + w_info.wind1 + "|" + w_info.tempF1);
                });
            }
        });
    }

	
    //运行 将你要表达的表示出来 调用方法就是咯
    getWeather var(function (weather_data) {
        //这里把拿到的weather_data放到你想要的位置。 
       return weather_date;
    }); 
</script>