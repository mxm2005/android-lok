package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import net.sf.json.JSONObject;

public class test {
    public static void main(String[] args) throws Exception {
//        String jsonString ="{\"name\":\"android\",\"version\":\"Beta1.0\"}";
//
//
//        JSONObject jbb = JSONObject.fromObject(jsonString);
//
//        String name = jbb.getString("name");
//        String version = jbb.getString("version");
//
//        System.out.println(name+"\n" +version);
        
        URL urll = new URL("http://61.4.185.48:81/g/");
        InputStreamReader isrr = new InputStreamReader(urll.openStream(), "utf-8");
        BufferedReader brr = new BufferedReader(isrr);
        StringBuilder sbb = new StringBuilder();
        String linee = "";
        while ((linee = brr.readLine()) != null) {
            sbb.append(linee);
        }
        System.out.println(sbb.toString());
        String info = sbb.toString();
        String id = info.substring(info.indexOf("id=") + 3, info.indexOf(";if"));
        System.out.println(id);
        
        URL url = new URL("http://m.weather.com.cn/data/" + id + ".html");
        InputStreamReader isr = new InputStreamReader(url.openStream(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());
        JSONObject jb = JSONObject.fromObject(sb.toString());
        JSONObject ja = jb.getJSONObject("weatherinfo");
        System.out.println(ja.getString("city"));
    }

//    /**
//     * 解析从淘宝返回的订单详情数据
//     * 
//     * @param jsonString
//     *            ：淘宝返回的JSON格式数据
//     * @return：订单详情数据
//     */
//    public Order analyticJson(String jsonString) {
//
//        // JSON格式数据解析对象
//        JSONObject jb = JSONObject.fromObject(jsonString);
//        JSONObject ja = jb.getJSONObject("trade_fullinfo_get_response")
//                .getJSONObject("trade");
//
//        // 需返回的订单对象
//        Order order = new Order();
//
//        order.setBuyer_nick(ja.getString("buyer_nick"));
//        order.setReceiver_address(ja.getString("receiver_address"));
//        order.setReceiver_city(ja.getString("receiver_city"));
//        order.setReceiver_name(ja.getString("receiver_name"));
//        order.setReceiver_state(ja.getString("receiver_state"));
//
//        // 区域是否存在
//        if (ja.containsKey("receiver_district")) {
//            order.setReceiver_district(ja.getString("receiver_district"));
//        } else {
//            order.setReceiver_district("");
//        }
//        // 手机号是否存在
//        if (ja.containsKey("receiver_mobile")) {
//            order.setReceiver_mobile(ja.getString("receiver_mobile"));
//        } else {
//            order.setReceiver_mobile("");
//        }
//        // 电话是否存在
//        if (ja.containsKey("receiver_phone")) {
//            order.setReceiver_phone(ja.getString("receiver_phone"));
//        } else {
//            order.setReceiver_phone("");
//        }
//
//        // 订单明细列表
//        java.util.List<OrderDetail> goodsList = new java.util.LinkedList<OrderDetail>();
//        JSONArray orderDetailArr = ja.getJSONObject("orders").getJSONArray(
//                "order");
//
//        // 循环添加订单明细
//        for (int j = 0; j < orderDetailArr.size(); j++) {
//            OrderDetail od = new OrderDetail();
//            od.setNum(orderDetailArr.getJSONObject(j).getString("num"));
//            od.setOuter_iid(orderDetailArr.getJSONObject(j).getString(
//                    "outer_iid"));
//            od.setPayment(orderDetailArr.getJSONObject(j).getString("payment"));
//            goodsList.add(od);
//        }
//        order.setGoodsList(goodsList);
//
//        return order;
//
//    }
}
