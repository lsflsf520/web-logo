package com.fshl.xy.logo.web.action;

import com.fshl.xy.logo.entity.Company;
import com.fshl.xy.logo.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by neko on 2016/12/24.
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @RequestMapping("save")
    public String save(Company company){
        companyService.saveEntity(company);

        return "redirect:/company/list.do";
    }

    @RequestMapping("toedit")
    public String toedit(HttpServletRequest request, Integer id){
        if(id != null){
            Company company = companyService.findById(id);
            request.setAttribute("company", company);
        }

        return "logo/company_edit";
    }

    @RequestMapping("list")
    public String list(HttpServletRequest request){
        List<Company> companyList = companyService.findAll();
        request.setAttribute("companyList", companyList);
        request.setAttribute("size", companyList.size());

        return "logo/company";
    }

}
