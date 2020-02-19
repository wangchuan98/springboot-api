package com.cc.controller;


import com.cc.common.JsonResult;
import com.cc.common.utils.ObjectUtil;
import com.cc.entity.*;
import com.cc.service.CoachService;
import com.cc.service.CourceService;
import com.cc.service.UserService;
import com.cc.vo.CoachVO;
import com.cc.vo.TableResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-09
 */
@Controller
@RequestMapping("/admin/coach")
public class AdminCoachController {
    @Autowired
    UserService userService;
    @Autowired
    CoachService coachService;
    @Autowired
    CourceService courceService;

    //非空检查时，可以为空的参数
    private final String[] COACH_SAVECANNULL = {"coachId", "tel"};
    private final String[] COACH_UPDATACANNULL = {"coachId", "tel", "username", "password"};

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/save")
    @ResponseBody
    public JsonResult save(HttpServletRequest request) throws Exception {
        //将request中的信息转化成VO
        CoachVO vo = this.transform(request);
        //校验空值
        if (ObjectUtil.objectIsEmpty(vo, COACH_SAVECANNULL))
            return JsonResult.error("必填参数存在空值");
        User result = userService.isExsitUsername(vo.getUsername());
        if (result != null) {
            //如果启用状态的，返回用户名已存在
            if (result.getEnable() == 0)
                return JsonResult.error("用户名已存在");
            else {
                //如果是注销的，就update原先的数据
                userService.updateByUserId(result.getUserId(), vo.getPassword(),User.CAOCH);
                String coachId = coachService.updatebyUserId(vo, result.getUserId());
                this.upload(request,coachId);
                return JsonResult.success("教练添加成功", coachId);
            }
        }
        String userId=userService.insertUser(vo.getUsername(),vo.getPassword(),User.CAOCH);
        String coachId=coachService.insertCoach(vo,userId);
        this.upload(request,coachId);
        return   JsonResult.success("教练添加成功",coachId);
    }
    @RequestMapping(value = "/coachlist")
    @ResponseBody
    public TableResultVO<Coach> adminCoachlist(HttpServletRequest request){

        String name=request.getParameter("name");
        String coachId=request.getParameter("coachId");
        Integer pageSize=Integer.valueOf(request.getParameter("pageSize"));
        Integer pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
        PageInfo pageInfo=new PageInfo(pageNumber,pageSize);
        List<Coach> list=coachService.queryByParam(name,coachId,pageInfo);
        //查询总数
        Integer count=coachService.queryCountByParam(name,coachId);
        TableResultVO<Coach> resultVO=new TableResultVO<>();
        resultVO.setRows(list);
        resultVO.setTotal(count);
        return  resultVO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResult delete(@RequestBody List<String> idList) throws Exception {
        if(idList.size()<=0)
        {
            return  JsonResult.error("请求参数为空");
        }
        //先查询是否有进行的课程
        List<Course> coutseList=courceService.queryForCid(idList);
        if(coutseList.size()>0){
            String msg="教练：";
            for(Course item:coutseList){
                msg=msg+item.getCoachid()+" ";
            }
            msg=msg+"仍存在进行的课程";
            return  JsonResult.error(msg);
        }
        //将启用标志改为失效，也就是1
        coachService.deletebyCoachId(idList);
        return  JsonResult.success("删除成功",null);


    }

    @RequestMapping(value = "/coachdetail")
    public String coachtDetail(HttpServletRequest request){
        this.setCoachInfo(request);
        return "coachdetail";
    }
    private  void setCoachInfo(HttpServletRequest request){
        String coachId=request.getParameter("coachId");
        CoachVO coach=coachService.queryByCoachId(coachId);
        if(coach!=null) {
            request.setAttribute("coachId", coach.getCoachId());
            request.setAttribute("name", coach.getName());
            request.setAttribute("sex", coach.getSex());
            request.setAttribute("age", coach.getAge());
            request.setAttribute("phone", coach.getTel());
            request.setAttribute("coachcar",coach.getCoachcar());
            request.setAttribute("teachtype",coach.getTeachtype());
            request.setAttribute("workphoto",coach.getWorkphoto());
        }
    }
    private CoachVO transform(HttpServletRequest request) {
        CoachVO vo = new CoachVO();
        vo.setUsername(request.getParameter("username"));
        vo.setPassword(request.getParameter("password"));
        vo.setCoachcar(request.getParameter("coachcar"));
        vo.setName(request.getParameter("name"));
        vo.setTeachtype(request.getParameter("teachtype"));
        System.out.println(request.getParameter("teachtype"));
        vo.setTel(request.getParameter("tel"));
        vo.setAge(Integer.valueOf(request.getParameter("age")));
        vo.setSex(Integer.valueOf(request.getParameter("sex")));
        return vo;
    }

    private  void upload(HttpServletRequest request,String CoachId) throws Exception {

        Part part = request.getPart("workphoto");
        String suffix = ".jpg";
        if (part != null) {
            String upload = "D:\\order\\workphoto";
            String filename = upload + "\\" + CoachId + suffix;
            part.write(filename);
        }
    }


}
