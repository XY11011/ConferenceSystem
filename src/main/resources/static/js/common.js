layui.use(['layer', 'jquery', 'form', 'laydate'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;
    laydate = layui.laydate;
    console.log('???')
    //邮箱正则
    var emailPattern = /^\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}$/;
    // 密码正则 最少6位，最多12位字母或数字的组合
    var pwdPattern = /^[a-zA-Z0-9]{6,12}$/;

    //执行一个laydate实例
    laydate.render({
        elem: '#birthday' //指定元素
    });

    // 表单验证
    form.verify({
        // 个人信息的验证
        // name: function (value) {
        //     // 保证名字不为空的时候，长度要在2-4之间
        //     if (value.length != 0 && (value.length < 2 || value.length > 4)) {
        //         return '名字长度要在2-4之间';
        //     }
        // },
        email: function (value) {
            // 保证邮箱不为空的时候，要满足邮箱的正则表达式
            if (value.length != 0 && !emailPattern.test(value)) {
                return '请输入一个合法的邮箱';
            }
        },
        // 密码的验证
        orginalPwd:function (value){
            if (value.length == 0) {
                return '原密码不能为空';
            }
        },
        newPwd: function (value) {
            if (value.length == 0) {
                return '新密码不能为空';
            }
            if (!pwdPattern.test(value)) {
                return '新密码需为6-12个字符';
            }
        },
        qrnewPwd: function (value) {
            if (value != $('#newPwd').val()) {
                return '两次密码要保持一致';
            }
        }

    })

    //修改用户个人信息的表单提交
    form.on('submit(formDemo)', function (data) {
        console.log('提交了了')
        $.ajax({
            type: "POST",
            url: "/updateUserInfo",  //路径
            data: data.field,
            success: function (data) {
                layer.alert('信息更新成功', function () {
                    window.location = '/'
                });

            }
        })
        return false;
    })

    //修改用户密码的表单提交
    form.on('submit(formDemo2)', function (data) {
        $.ajax({
            type: "POST",
            url: "/updateUserPwd",  //路径
            data: data.field,
            success: function (data) {
                // 如果原密码输入错误 则提示
                if(data==-1){
                    layer.alert('原密码不正确');
                }else if(data==1){
                    layer.alert('密码更新成功', function () {
                        window.location = '/'
                    });
                }else{
                    layer.alert('密码更新失败');
                }
            }
        })
        return false;
    })

    //修改审稿人个人信息的表单提交
    form.on('submit(formDemo3)', function (data) {
        $.ajax({
            type: "POST",
            url: "/updateReviewerInfo",  //路径
            data: data.field,
            success: function (data) {
                layer.alert('信息更新成功', function () {
                    // 回到审稿人主页
                    window.location = '/'
                });

            }
        })
        return false;
    })

    //修改用户密码的表单提交
    form.on('submit(formDemo4)', function (data) {
        $.ajax({
            type: "POST",
            url: "/updateReviewerPwd",  //路径
            data: data.field,
            success: function (data) {
                // 如果原密码输入错误 则提示
                if(data==-1){
                    layer.alert('原密码不正确');
                }else if(data==1){
                    layer.alert('密码更新成功', function () {
                        // 回到审稿人主页
                        window.location = '/'
                    });
                }else{
                    layer.alert('密码更新失败');
                }
            }
        })
        return false;
    })
})