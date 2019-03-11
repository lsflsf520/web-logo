package com.fshl.xy.weizhan.entity;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fshl.xy.weizhan.constant.OptionType;
import com.xyz.tools.common.constant.Bool;
import com.xyz.tools.common.utils.StringUtil;
import com.xyz.tools.db.bean.BaseEntity;

public class ConsultConfig extends BaseEntity<Integer> {
    private Integer id;

    private Integer buid;

    private Integer siteId;

    private String fieldName;

    private OptionType optionType;

//    private String option;
    private Set<String> options;
    
    private Bool required;

    private Integer priority;

    private Date createTime;

    private Date lastUptime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuid() {
        return buid;
    }

    public void setBuid(Integer buid) {
        this.buid = buid;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public String getOpt() {
		return CollectionUtils.isEmpty(this.options) ? null : StringUtils.join(this.options, ",");
	}

	public void setOpt(String opt) {
		this.options = StringUtil.toSet(opt);
	}
	
	public Set<String> getOptions() {
		return options;
	}

	public void setOptions(Set<String> options) {
		this.options = options;
	}

	public Bool getRequired() {
		return required;
	}

	public void setRequired(Bool required) {
		this.required = required;
	}

	public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUptime() {
        return lastUptime;
    }

    public void setLastUptime(Date lastUptime) {
        this.lastUptime = lastUptime;
    }

    @Override
    public Integer getPK() {
        return id;
    }
}