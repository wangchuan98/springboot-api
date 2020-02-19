package com.cc.controller.api;

import com.cc.common.JsonResult;
import com.cc.common.utils.ObjectUtil;
import com.cc.common.utils.StringUtil;
import com.cc.entity.Evaluation;
import com.cc.service.EvaluationService;
import com.cc.vo.EvaluationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-14
 */
@Controller
@RequestMapping("/api")
public class EvaluationController {

    @Autowired
    EvaluationService evaluationService;
    private final  String[]  SAVECANNULL={"evaluationId","formateDate","studentName","studentFace"};
    @RequestMapping("/evaluationpublish")
    @ResponseBody
    public JsonResult publish(@RequestBody EvaluationVO evaluationVO){
        //属性空值判断
           if( ObjectUtil.objectIsEmpty(evaluationVO,SAVECANNULL)){
               return  JsonResult.error("发布失败");
           }
           evaluationService.publish(evaluationVO);
           return  JsonResult.success("发布成功",null);
    }



    @RequestMapping("/queryevaluation")
    @ResponseBody
    public JsonResult query(@RequestParam("coachId") String coachId,@RequestParam("page")Integer page){
        if(StringUtil.isEmpty(coachId)||page<Integer.valueOf(1)){
            return  JsonResult.error("查询参数错误");
        }
        List<EvaluationVO> resultVO= evaluationService.queryEvaluationByCoachId(coachId,page);
        return  JsonResult.success("查询成功",resultVO);
    }

}
