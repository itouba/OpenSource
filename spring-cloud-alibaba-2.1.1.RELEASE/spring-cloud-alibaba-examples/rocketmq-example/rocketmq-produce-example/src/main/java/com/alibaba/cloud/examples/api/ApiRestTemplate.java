package com.alibaba.cloud.examples.api;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class ApiRestTemplate {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static int retryCount = 0;
    public static int failedCount = 0;

    /**
     * 向目的URL发送Get请求
     * @param url       目的url
     * @param params    发送的参数
     * @return  ResultVO
     */
    public ResultVO sendGetRequest(String url, MultiValueMap<String, String> params){
        HttpHeaders headers = new HttpHeaders();
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<ResultVO> response = restTemplate.getForEntity(url, ResultVO.class, requestEntity);

        return response.getBody();
    }

    /**
     * 向目的URL发送Post请求
     * @param url       目的url
     * @param params    发送的参数
     * @return  ResultVO
     */
    public ResultVO sendPostRequest(String url, MultiValueMap<String, String> params){
        HttpHeaders headers = new HttpHeaders();
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<ResultVO> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResultVO.class);

        return response.getBody();
    }


    public ResultVO sendGetAPIRequest(String url, MultiValueMap<String, String> params, int delay){
        sendGetRequest(url, params);
        String reurl = url.replace("/cmd/", "/result/");
        try {
            Thread.sleep(delay);
            ResultVO resultVO = sendGetRequest(reurl, params);
            if ("failed".equals(resultVO.getMessage())){
                retryCount++;
                System.out.println("sendGetRequest retry:"+reurl);
                Thread.sleep(delay);
                ResultVO resultVO2 = sendGetRequest(reurl, params);
                if ("failed".equals(resultVO2.getMessage())){
                    failedCount++;
                }
            }
            return resultVO;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultVO(800, "interrupted");
    }

    public ResultVO sendPostAPIRequest(String url, MultiValueMap<String, String> params, int delay){
        sendPostRequest(url, params);
        String reurl = url.replace("/cmd/", "/result/");
        try {
            Thread.sleep(delay);
            ResultVO resultVO = sendPostRequest(reurl, params);
            if ("failed".equals(resultVO.getMessage())){
                retryCount++;
                System.out.println("sendGetRequest retry:"+reurl);
                Thread.sleep(delay);
                ResultVO resultVO2 = sendPostRequest(reurl, params);
                if ("failed".equals(resultVO2.getMessage())){
                    failedCount++;
                }
                return resultVO2;
            }
            return resultVO;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultVO(800, "interrupted");
    }

    public ResultVO sendGetAPIRequest(String url, int delay){
        LinkedMultiValueMap<String, String> params  = new LinkedMultiValueMap<>();
        return sendGetAPIRequest(url, params , delay);
    }

    public ResultVO sendPostAPIRequest(String url, int delay){
        LinkedMultiValueMap<String, String> params  = new LinkedMultiValueMap<>();
        return sendPostAPIRequest(url, params, delay);
    }
}
