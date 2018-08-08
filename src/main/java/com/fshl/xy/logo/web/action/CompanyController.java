package com.fshl.xy.logo.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by neko on 2016/12/24.
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    private final static Logger LOG = LoggerFactory.getLogger(CompanyController.class);

//    @Autowired
//    private CompanyServiceImpl companyService;
//
//    @Autowired
//    private ContactCrawlService contactCrawlService;
//
//    @RequestMapping("save")
//    public String save(Company company){
//        companyService.saveEntity(company);
//
//        return "redirect:/company/list.do";
//    }
//
//    @RequestMapping("toedit")
//    public String toedit(HttpServletRequest request, Integer id){
//        if(id != null){
//            Company company = companyService.findById(id);
//            request.setAttribute("company", company);
//        }
//
//        return "logo/company_edit";
//    }
//
//    @RequestMapping("list")
//    public String list(HttpServletRequest request){
//        List<Company> companyList = companyService.findAll();
//        request.setAttribute("companyList", companyList);
//        request.setAttribute("size", companyList.size());
//
//        return "logo/company";
//    }
//
//    @RequestMapping("crawlFrom36kr")
//    @ResponseBody
//    public String crawlFrom36kr(){
//        try {
//            contactCrawlService.crawlCompany();
//        } catch (IOException e) {
//            LOG.error(e.getMessage(), e);
//        }
//
//        return "OK";
//    }

}
