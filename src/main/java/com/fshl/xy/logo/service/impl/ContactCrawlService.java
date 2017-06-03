package com.fshl.xy.logo.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fshl.xy.logo.dao.impl.CompanyDaoImpl;
import com.fshl.xy.logo.entity.Company;
import com.google.gson.Gson;
import com.ujigu.secure.common.utils.RegexUtil;

/**
 * Created by neko on 2017/1/2.
 */
@Service
public class ContactCrawlService {

    private final static Logger LOG = LoggerFactory.getLogger(ContactCrawlService.class);

    @Autowired
    private CompanyDaoImpl companyDao;

    public static void main(String[] args) throws IOException {
        /*Connection.Response response = Jsoup.connect("https://passport.36kr.com/passport/sign_in").ignoreContentType(true)
                .data("username", "lsflsf520@126.com",
                      "password", "5869335",
                      "needCaptcha", "false",
                      "bind", "false",
                      "type", "login",
                      "ok_url", "http%3A%2F%2F36kr.com%2F",
                      "ktm_reghost", "36kr.com")
                .header("Cookie", "aliyungf_tc=AQAAAEsU8hxP1QAAf1/2cb1iS0ZZR50q; ktm_ab_test=t.5_v.10; Hm_lvt_713123c60a0e86982326bae1a51083e1=1482547546; Hm_lpvt_713123c60a0e86982326bae1a51083e1=1483321672; kr_stat_uuid=bCbJB24722027; krnewsfrontss=1bd20c35f5bd92328f3e1429166038bc; kwlo_iv=3h; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1482548003,1482633734,1483321099; Hm_lpvt_e8ec47088ed7458ec32cde3617b23ee3=1483321676")
                .header("Host", "passport.36kr.com")
                .header("Origin", "https://passport.36kr.com")
                .header("Referer", "https://passport.36kr.com/pages/?ok_url=http%3A%2F%2F36kr.com%2F")
                .header("Accept", "application/json, text/plain")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.8")
                .method(Connection.Method.POST)
                .execute();
//        Document doc = response.parse();
        Map<String,String> cookies = response.cookies();
        System.out.println(cookies);*/

//        Document doc = Jsoup.connect("https://rong.36kr.com/company/list/?city=101&page=2").cookies(cookies).get();
//        String body = Jsoup.connect("https://rong.36kr.com/api/company?city=101&fincestatus=1&page=2&type=2")
//                .execute().body();

//        String body = HttpClientUtil.getInstance().doGet("https://rong.36kr.com/api/company?city=101&fincestatus=1&page=2&type=2");
//        System.out.println(body);
    }

    public void crawlCompany() throws IOException {
        Set<Company> companies = new HashSet<>();
//        String body = buildConn("https://rong.36kr.com/api/company?&fincestatus=1&page=2").execute().body();
        String body = buildConn("https://rong.36kr.com/n/api/column/0/company?p=1").execute().body();
        Map<String, Object> valMap = new Gson().fromJson(body, Map.class);
        Map<String, Object> pageMap = (Map<String, Object>) ((Map)valMap.get("data")).get("pageData");

        Object pageSize = pageMap.get("pageSize");
        Object totalCount = pageMap.get("totalCount");
        int totalPages = Double.valueOf(pageMap.get("totalPages").toString()).intValue();
        System.out.println(pageSize + "," + totalCount + "," + totalPages);

        int count = 1;
        do{
            try{
//                body = buildConn("https://rong.36kr.com/api/company?&fincestatus=1&page=" + count).execute().body();
                body = buildConn("https://rong.36kr.com/n/api/column/0/company?p=" + count).execute().body();
                valMap = new Gson().fromJson(body, Map.class);
                pageMap = (Map<String, Object>) ((Map)valMap.get("data")).get("pageData");
                List<Map<String, Object>>  datas = (List<Map<String, Object>>) pageMap.get("data");
                for(Map<String, Object> data : datas){
//                    Map<String, Object> companyMap = (Map<String, Object>)data.get("company");
                    Company company = new Company();
                    company.setName((String)data.get("name"));

//                    Connection conn = buildConn("https://rong.36kr.com/api/company/"+(Double.valueOf(companyMap.get("id").toString()).intValue()));
                    Connection conn = buildConn("https://rong.36kr.com/n/api/company/"+(Double.valueOf(data.get("id").toString()).intValue())+"/product/");
                    String infoStr = conn.execute().body();
//            System.out.println(companyMap.get("id") + "," + companyMap.get("name"));
                    valMap = new Gson().fromJson(infoStr, Map.class);
                    Map<String, Object> detailMap = (Map<String, Object>) ((Map)valMap.get("data")).get("companyProduct");
                    String webLink = (String)detailMap.get("website");
                    if(StringUtils.isNotBlank(webLink)){
                        company.setUrl(webLink);
                        companies.add(company);
                        try{
//                    String webLink = "http://www.yescai.com";
                            System.out.println(webLink);
                            Document doc = Jsoup.parse(new URL(webLink), 10000);
                            String content = doc.html().replaceAll("\\s+", "");
                            List<String> phones = RegexUtil.extractGroups(".*[>:\"']+([\\d\\-]{7,30})[<\"'].*", content);
                            List<String> emails = RegexUtil.extractGroups(".*[>:\"']+(\\w+@[\\w\\.]+)[<\"'].*", content);
                            List<String> contactUrls = RegexUtil.extractGroups(".*href=\"([^\"'](contact|Contact)[^\"']*)\".*", doc.html());

                            String contactUrl = null;
                            if(contactUrls != null && !contactUrls.isEmpty()){
                                contactUrl = contactUrls.get(0);
                                if(contactUrl.startsWith("//")){
                                    contactUrl = "http:" + contactUrl;
                                }else if(contactUrl.startsWith("/")){
                                    contactUrl = webLink + contactUrl;
                                } else if(!contactUrl.startsWith("http://")){
                                    contactUrl = webLink + "/" + contactUrl;
                                }
                            }
                            System.out.println(webLink + "," + contactUrl + "," + phones + "," + emails);

                            if(StringUtils.isNotBlank(contactUrl) && (CollectionUtils.isEmpty(phones) || CollectionUtils.isEmpty(emails))){
                                try{
                                    Document contactDoc = Jsoup.parse(new URL(contactUrl), 10000);
                                    content = contactDoc.html().replaceAll("\\s+", "");
                                    phones = RegexUtil.extractGroups(".*[>:\"']+([\\d\\-]{7,30})[<\"'].*", content);
                                    emails = RegexUtil.extractGroups(".*[>:\"']+(\\w+@[\\w\\.]+)[<\"'].*", content);
                                }catch (Exception e){
                                    LOG.error("contactUrl:" + contactUrl + ",errorMsg:" + e.getMessage(), e);
                                }
                            }

                            if(!CollectionUtils.isEmpty(phones)){
                                company.setPhone(phones.get(0));
                            }

                            if(!CollectionUtils.isEmpty(emails)){
                                company.setEmail(emails.get(0));
                            }
                        } catch (Exception e){
                            LOG.error("webLink:" + webLink + ",errorMsg:" + e.getMessage(), e);
                        }
                    }

                }
            }catch (Exception e){
                LOG.warn("count:" + count + ",errorMsg:" + e.getMessage(), e);
            }

            System.out.println("count:" + count + ",company size:" + companies.size());
        }while (count++ <= totalPages);
//        Object pageSize = pageMap.get("pageSize");
//        Object totalCount = pageMap.get("totalCount");
//        Object totalPages = pageMap.get("totalPages");
        System.out.println(pageSize + "," + totalCount + "," + totalPages + ",company size:" + companies.size());
        if(!companies.isEmpty()){
            FileUtils.write(new File("~/data/company.txt"), new Gson().toJson(companies));
            companyDao.insertBatch(new ArrayList<Company>(companies));
        }
    }

    private static Connection buildConn(String url){
        return Jsoup.connect(url).ignoreContentType(true)
                .header("Upgrade-Insecure-Requests", "1")
                .header("Host", "rong.36kr.com")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Encoding", "gzip, deflate, sdch, br")
                .header("Accept-Language", "zh-CN,zh;q=0.8")
//                .header("Cookie", "aliyungf_tc=AQAAAGsUtSpNaAEAf1/2ceI4vqDdLmrr; kr_stat_uuid=bCbJB24722027; kwlo_iv=3h; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1482548003,1482633734,1483321099; Hm_lpvt_e8ec47088ed7458ec32cde3617b23ee3=1483321676; gr_user_id=f00f6644-a40c-4fd0-942b-6e0a788e42e0; _kr_p_se=3a71ed3f-01a9-4cf7-88e0-d6bdc0f9a1e6; krid_user_id=1279564941; krid_user_version=1001; kr_plus_id=1279564941; kr_plus_token=kFNWiST92zsbFf47wqXcCAej9pwmBy16989_____; ktm_ab_test=t.5_v.10!t.6_v.default; Hm_lvt_713123c60a0e86982326bae1a51083e1=1482547546; Hm_lpvt_713123c60a0e86982326bae1a51083e1=1483326527; kr_plus_utype=0; device-uid=c7253690-d098-11e6-9c4e-3145f380880e; krnewsfrontss=40170c6ca6dc0e4722ae49c7f9e29a77; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1482548003,1482633734,1483321099; Hm_lpvt_e8ec47088ed7458ec32cde3617b23ee3=1483326531; gr_session_id_76d36bd044527820a1787b198651e2f1=d8e2bdcc-797a-4de6-b890-e85d95d32da5; Z-XSRF-TOKEN=eyJpdiI6Ikx3djJ3VUk5bitiKzFkamJPWmFcL1lRPT0iLCJ2YWx1ZSI6InpCUkpxTkFFb1hSTmR3cldnb1FySmlsc251UWhObTVLK1l4THFGV0dBZGFxdTRvNkErQVVPbHBcL2twYVwvV0VYWXRoUFl2OHFIQXNWbFhrZ0QwNStHSkE9PSIsIm1hYyI6IjVlYmQ0ZWVmYWY1OTJlYzc3MWJiNzcwYzI3NTdiZWM2YjMzMWRlMzY3ZWIwYThiZDI3NWM2N2RjZWE1YzZhNGUifQ%3D%3D; krchoasss=eyJpdiI6Ijg4ekNjZHpoZkdxTnRZNzJ3bHpnMUE9PSIsInZhbHVlIjoiXC92QW1FT1NHSjZSSHpTbEdyQzFmbkN5VFczVlJXdkR5b1BPOXZKVTRGaFBUZUVMaU8zZ0hNOVA1QWJBMU1rVkUweHhFRHp3cGJSSE1Jc25MeWpYVGJRPT0iLCJtYWMiOiI5MzllNzcyYWMwZjFjM2M3M2RiMDAyNjgyMjU5YzI5MTY0NTcyZGM5ZWE0Y2JkNDdlMGVlODYzNDg1Y2I0NGVhIn0%3D")
                .header("Cookie", "kr_plus_id=1279564941; kr_plus_token=kFNWiST92zsbFf47wqXcCAej9pwmBy16989_____;");
    }

}
