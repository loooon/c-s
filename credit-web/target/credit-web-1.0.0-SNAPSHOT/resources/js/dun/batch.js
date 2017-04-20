/**
 * Created by admin on 3/22/2017.
 */

//保存
function btnAdd() {
    $("#batch_data_div").hide();
    $("#resultSizeP").hide();
    $(".file").css("min-height",300);
    var formData = new FormData($("#query_batch_Form")[0]);

    var filePath=$('#file_id').val();
    if(filePath==""){
        $("#error_message_p").css("display",'block');
        $("#error_message").text("请选择文件");
        return;
    }
    var ext=filePath.substr(filePath.lastIndexOf(".")).toLowerCase();

    if (ext!=".txt" && ext!=".csv") {
        $("#error_message_p").css("display",'block');
        $("#error_message").text('只支持文件格式txt、csv');
        $('#file_id').val("");
        $("#fileTxt").text('选择上传文件');
        return;
    }
    var file=$('#file_id')[0];
    var byteSize  = file.files[0].size;


    if (byteSize > 2097152) {
        $("#error_message_p").css("display",'block');
        $("#error_message").text('请上传2M以内的文件');
        $('#file_id').val("");
        $("#fileTxt").text('选择上传文件');
        return;
    }
        //$("#error_message").html('上传号码文件（支持文件格式txt、csv）');
    $("#error_message_p").css("display",'block');
    $("#error_message").text('上传中..');

    $.ajax({
        url: "/dun/batch",
        method: "POST",
        data: formData,
        dataType:'json',
        async:true,
        contentType: false, //必须false才会避开jQuery对 formdata 的默认处理 XMLHttpRequest会对 formdata 进行正确的处理
        processData: false, //必须false才会自动加上正确的Content-Type
        success: function (data) {
            if (data.result==200) {
                $("#error_message_p").css("display",'none');
                var data = data.data;
                var resultList = data.resultList;
                var html = '';
                resultList.forEach(function (value, index, array)
                {
                    var str = value.orgName;
                    if(value.demandName!=null){
                        str+="·"+value.demandName;
                    }
                    var phoneNumber = value.phoneNumber;
                    if (phoneNumber&&phoneNumber.length>9&&phoneNumber.indexOf("0")==0) {
                        if(phoneNumber.indexOf("01")==0||phoneNumber.indexOf("02")==0){
                            phoneNumber = phoneNumber.substring(0,3)+"-"+phoneNumber.substring(3,phoneNumber.length);
                        }else {
                            phoneNumber = phoneNumber.substring(0,4)+"-"+phoneNumber.substring(4,phoneNumber.length);
                        }
                    }
                     html += '<div class="result-block01"> <div class="res-left01"> <img src="/resources/img/dun/'+value.demandImg+'">' +
                        ' </div> <div class="res-right01"> <h4>'+str+'</h4> ' +
                        '<h5>电话号码：'+phoneNumber+'</h5> </div> </div>';
                });
                if(data.isGreaterThan)
                {
                    html+='<div class="adv">'+
                        '<h3>多查验结果，请通过集团用户通道联系我们，为您提供更好的服务！<a href="/dun/company">立即前往</a></h3>'+
                    '</div>';
                }
                $("#batch_data_div").html(html);
                $("#batch_data_div").show();
                $(".file").css("min-height",0);
                $("#resultSize").text(data.maxSize);
                $("#findNumberCount").text(data.findNumberCount);
                $("#resultSizeP").show();
            }else if(data.result==6){
                window.location.href = "/login";
            }
            else {
                $("#error_message_p").css("display",'block');
                $("#error_message").text("未找到到电话号码");
                $("#batch_data_div").html('');
                $("#batch_data_div").hide();
                $(".file").css("min-height",300);
                $("#resultSize").hide();
            }
        }
    });
}


$(document).ready(function ()
{
    window.document.onkeydown =function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){ // enter 键

            btnAdd();
            $("#file_id").blur();
        }

    };

    $("#upload_file").on('click',function ()
    {
        btnAdd();
    });
    $("#file_id").on('change',function ()
    {
        if ($(this).val() != "") {

            $("#fileTxt").text($(this).val());
        }else{
            $("#fileTxt").text('选择上传文件');
        }
    });
});