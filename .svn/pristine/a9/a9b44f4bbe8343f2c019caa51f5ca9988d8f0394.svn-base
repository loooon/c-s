package com.credit.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.credit.common.util.CollectionUtil;
import com.credit.dto.GrayscaleVariateDto;
import com.credit.entity.UserGrayscaleStatVariable;
import com.credit.repository.UserGrayscaleStatVariableRepository;
import com.credit.service.UserGrayscaleStatVariableService;

/**
 * Created by wangjunling on 2017/4/13.
 */
@Service("userGrayscaleStatVariableService")
public class UserGrayscaleStatVariableServiceImpl implements UserGrayscaleStatVariableService
{
    @Resource
    private UserGrayscaleStatVariableRepository userGrayscaleStatVariableRepository;

    @Override
    public List<GrayscaleVariateDto> searchDtoByVkey(String vkey, Date applyDate)
    {
        List<UserGrayscaleStatVariable> variableList = userGrayscaleStatVariableRepository.findByUserVkey(vkey);
        if (CollectionUtil.isEmpty(variableList))
        {
            return null;
        }
        List<GrayscaleVariateDto> variateDtos = new ArrayList<>();
        for (UserGrayscaleStatVariable variable : variableList)
        {
            variateDtos.add(convert(variable, applyDate));
        }
        return variateDtos;
    }

    /**
     * @param dateVar
     *            l1m l2m l3m l1w l2w
     * @return
     */
    private Date parse(Date appleDate, String dateVar)
    {
        if (StringUtils.isBlank(dateVar) || dateVar.equals("all"))
        {
            return null;
        }
        int num = Integer.valueOf(String.valueOf(dateVar.charAt(1)));
        String unit = String.valueOf(dateVar.charAt(2));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(appleDate);
        if (unit.equals("m"))
        {
            calendar.add(Calendar.MONTH, -num);
        }
        else
            if (unit.equals("w"))
            {
                calendar.add(Calendar.DAY_OF_WEEK, -num);
            }
        return calendar.getTime();
    }

    private GrayscaleVariateDto convert(UserGrayscaleStatVariable variable, Date applyDate)
    {
        GrayscaleVariateDto dto = new GrayscaleVariateDto();
        Date lastDate = parse(applyDate, variable.getVariableDate());
        dto.setVariateName(variable.getGrayscaleVariable().getVariableName());
        dto.setLastDateTag(variable.getVariableDate());
        dto.setLastDate(lastDate);
        dto.setDun(variable.getVariableDun());
        dto.setTimeTag(variable.getVariableTime());
        String criteriaName = dto.getLastDateTag() + dto.getTimeTag() + dto.getDun();
        if (StringUtils.isNotBlank(criteriaName))
        {
            dto.setAllVariateName(criteriaName + "_" + dto.getVariateName());
            dto.setCriteriaName(criteriaName);
        }
        return dto;
    }
}
