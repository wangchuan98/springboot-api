function skilldelete(id){

    var subject=parseInt($("#subjectTabContent").children(".active").attr("data-subject"));
     alert(subject);
     var page=1;
     if(subject==2){
         page=twocurrentpage;
     }else {
         page=threecurrentpage;
     }
     console.log("twocurrentpage"+twocurrentpage);
    console.log("threecurrentpage"+threecurrentpage);
    console.log("page"+page);
    $.ajax(
        {
            url:"/admin/skilldelete?id="+id,
            type:"POST",
            dateType:"json",
            success: function (data) {
                alert(data.msg);
                var  reloadurl="/admin/skilllist?pageNumber="+page+"&category="+subject;
                if(subject==2){
                    $("#subject2_list").load(reloadurl);
                }else {
                    $("#subject3_list").load(reloadurl);
                }


            },
            error:function () {
                alert("网络错误");
                console.log("1 异步调用返回失败,XMLHttpResponse.readyState:"+XMLHttpResponse.readyState);
                console.log("2 异步调用返回失败,XMLHttpResponse.status:"+XMLHttpResponse.status);
                console.log("3 异步调用返回失败,textStatus:"+textStatus);
                console.log("4 异步调用返回失败,errorThrown:"+errorThrown);
            }
        }
    )
}

