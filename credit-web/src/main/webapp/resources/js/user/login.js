$(document).ready(function () {
    particlesJS("particles-js", {
        particles: {
            number: {value: 80, density: {enable: !0, value_area: 800}},
            color: {value: "#ffffff"},
            shape: {
                type: "circle",
                stroke: {width: 0, color: "#000000"},
                polygon: {nb_sides: 5},
                image: {src: "img/github.svg", width: 100, height: 100}
            },
            opacity: {value: .5, random: !1, anim: {enable: !1, speed: 1, opacity_min: .1, sync: !1}},
            size: {value: 3, random: !0, anim: {enable: !1, speed: 40, size_min: .1, sync: !1}},
            line_linked: {enable: !0, distance: 150, color: "#ffffff", opacity: .4, width: 1},
            move: {
                enable: !0,
                speed: 6,
                direction: "none",
                random: !1,
                straight: !1,
                out_mode: "out",
                bounce: !1,
                attract: {enable: !1, rotateX: 600, rotateY: 1200}
            }
        },
        interactivity: {
            detect_on: "canvas",
            events: {onhover: {enable: !0, mode: "grab"}, onclick: {enable: !0, mode: "push"}, resize: !0},
            modes: {
                grab: {distance: 140, line_linked: {opacity: 1}},
                bubble: {distance: 400, size: 40, duration: 2, opacity: 8, speed: 3},
                repulse: {distance: 200, duration: .4},
                push: {particles_nb: 4},
                remove: {particles_nb: 2}
            }
        },
        retina_detect: !0
    });


    $("#to_login").click(function ()
    {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username == "")
        {
            $(".login-d .error span").html("请输入用户名").show();
            return;
        }
        if (password == "")
        {
            $(".login-d .error span").html("请输入密码").show();
            return;
        }
        if (username != "" && password != "")
        {
            $(this).attr("value", "登录中...");
            $.ajax({
                url: "/login",
                method: "POST",
                // timeout: 5e3,
                // async:true,
                dataType: "json",
                data: {name: username, password: password},
                error: function ()
                {
                    $(".login").attr("value", "登录")
                },
                success: function (t)
                {
                    if (t.result == 0)
                    {
                        window.location.href = "/user/index"
                    } else
                        {
                        $(".login").attr("value", "登录");
                        $(".login-d .error span").html(t.message);
                        $(".login-d .error span").show();
                    }
                }
            })
        }

    });

    $(".pwd-d .getpwd").on('click', function ()
    {
        var email = $("#email").val();
        if (email)
        {
            $.ajax({
                url: "password",
                method: "POST",
                dataType: "json",
                data: {userName: email},
                error: function ()
                {
                },
                success: function (t)
                {
                    if (t.result != 200)
                    {
                        $(".pwd-d .error span").html(t.message).show();
                    } else
                        {
                        $(".get-d .tr3").html(t.message).show();
                        $(".get-d").show();
                        $(".pwd-d").hide();
                    }
                }
            })
        } else {
            $(".pwd-d .error span").html("请输入用户名").show();
        }


    });

    $("#getPwd").on('click', function ()
    {
        $(".login-d").hide();
        $(".pwd-d").show();
    });

    $(".get-d .login-button").on('click', function ()
    {
        $(".get-d").hide();
        $(".login-d").show();
    });

    $("#back-login").on('click', function ()
    {
        $(".login-d .error span").hide();
        $(".pwd-d").hide();
        $(".login-d").show();
    });

    $(".login-d input").on('keydown', function (t)
    {
        if (13 == t.which)
        {
            $("#to_login").click();
        }
    });

    $(".pwd-d input").on('keydown', function (t)
    {
        if (13 == t.which) {
           $(".pwd-d .getpwd").click();
        }
    });
});