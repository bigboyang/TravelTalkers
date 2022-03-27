
    let code = "";

    // 유효성 검사

    let idCheck = false;
    let idckCheck = false;
    let pwCheck = false;
    let pwckCheck = false;
    let pwckckCheck = false;
    let nameCheck = false;
    let mailCheck = false;
    let mailnumCheck = false;
    let addressCheck = false;


    $(document).ready(function (){
        //회원가입 버튼 로직
        $(".join_button").click(function (){

            let id = $(".id_input").val();
            let pw = $('.pw_input').val();
            let pwck = $(".pwck_input").val();
            let name= $('.user_input').val();
            let mail = $('.mail_input').val();
            let addr = $('.address_input_3').val();

            if (id == ""){
                $('.final_id_ck').css('display','block');
                idCheck = false;
            } else {
                $('.final_id_ck').css('display','none');
                idCheck = true;
            }

            if (pw == ""){
                $('.final_pw_ck').css('display','block');
                pwCheck = false;
            } else {
                $('.final_pw_ck').css('display','none');
                pwCheck = true;
            }

            if (pwck == ""){
                $('.final_pwck_ck').css('display','block');
                pwckCheck = false;
            } else {
                $('.final_pwck_ck').css('display','none');
                pwckCheck = true;
            }

            if (name == ""){
                $('.final_name_ck').css('display','block');
                nameCheck = false;
            } else {
                $('.final_name_ck').css('display','none');
                nameCheck = true;
            }

            if (mail == ""){
                $('.final_mail_ck').css('display','block');
                mailCheck = false;
            } else {
                $('.final_mail_ck').css('display','none');
                mailCheck = true;
            }

            if (addr == ""){
                $('.final_addr_ck').css('display','block');
                addressCheck = false;
            } else {
                $('.final_addr_ck').css('display','none');
                addressCheck = true;
            }

            if(idCheck&&idckCheck&&pwCheck&&pwckCheck&&pwckckCheck&&nameCheck&&mailCheck&&mailnumCheck&&addressCheck){
                $("#signup_form").attr("action","/user/signup");
                $("#signup_form").submit();
            }

            return false;

        })
    })

    $('.id_input').on("propertychange change keyup paste input",function (){

        let memberId = $('.id_input').val();
        let data = {memberId : memberId}
        let Msg = $('.id_input_box_warn');

        if(idFormCheck(memberId)){
            Msg.html("적합한 아이디 입니다.");
            Msg.css("display","inline-block");
        } else {
            Msg.html("영문자 또는 숫자 6~20");
            Msg.css("display","inline-block");
            return false;
        }

        $.ajax({
            type : "post",
            url : "/user/userIdCheck",
            data : data,
            success : function (result){
                if(result != 'fail'){
                    $('.id_input_re_1').css("display","inline-block") // 성공
                    $('.id_input_re_2').css("display","none") // 실패
                    idckCheck = true;
                } else{
                    $('.id_input_re_2').css("display","inline-block")
                    $('.id_input_re_1').css("display","none")
                    idckCheck = false;
                }

            }
        });

    });


    $(".mail_check_button").click(function (){

        let email = $(".mail_input").val();
        let checkBox = $(".mail_check_input");
        let boxWrap = $(".mail_check_input_box");
        let warnMsg = $(".mail_input_box_warn");

        if(mailFormCheck(email)){
            warnMsg.html("이메일이 전송 되었습니다. 이메일을 확인해 주세요");
            warnMsg.css("display","inline-block");
        } else {
            warnMsg.html("올바르지 못한 이메일 형식입니다.");
            warnMsg.css("display","inline-block");
            return false;
        }

        $.ajax({

            type: "GET",
            url:"/user/mailCheck?email="+email,
            success:function (data){
                checkBox.attr("disabled", false);
                boxWrap.attr("id","mail_check_input_box_true");
                code = data;
            }

        });

    });

    $(".mail_check_input").blur(function (){

        let inputCode = $(".mail_check_input").val();
        let checkResult = $('#mail_check_input_box_warn');

        if(inputCode == code){
            checkResult.html("인증 번호가 일치합니다.");
            checkResult.attr("class","correct");
            mailnumCheck = true;
        } else{
            checkResult.html("인증번호를 다시 확인해주세요");
            checkResult.attr("class","incorrect");
            mailnumCheck = false;
        }

    });

    function execution_daum_address() {
        new daum.Postcode({
            oncomplete: function (data) {

                let addr = ''; // 주소 변수
                let extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }

                    // 주소 변수 문자열 합치기
                    addr += extraAddr;

                    } else {
                        addr += " ";
                    }

                    // 우편번호와 주소 정보를 필드에 넣는다.
                    $(".address_input_1").val(data.zonecode);

                    $(".address_input_2").val(addr);


                    // 커서를 상세주소 필드로 이동한다.
                    $(".address_input_3").attr("readonly",false);
                    $(".address_input_3").focus();


            }
        }).open();
    }

    $('.pwck_input').on("propertychange change keyup paste input", function (){

        let pw = $('.pw_input').val();
        let pwck = $('.pwck_input').val();
        let Msg = $('.pw_input_box_warn');

        if (pwFormCheck(pw)){
            Msg.html("올바른 비밀번호 입력을 하였습니다.");
            Msg.css("display","inline-block");
        } else{
            Msg.html("제대로 적으시오 비밀번호 정규식 특수문자 / 문자 / 숫자 포함 형태의 8~20");
            Msg.css("display","inline-block");
            return false;
        }

        $('.final_pwck_ck').css('display','none');

        if(pw == pwck){
            $('.pwck_input_re_1').css('display','block');
            $('.pwck_input_re_2').css('display','none');
            pwckckCheck = true;
        } else{
            $('.pwck_input_re_2').css('display','block');
            $('.pwck_input_re_1').css('display','none');
            pwckckCheck = false;
        }

    });

    function mailFormCheck(email){
        let form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        return form.test(email);
    }

    function pwFormCheck(pw){
        let formpw = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
        return formpw.test(pw);
    }

    function idFormCheck(id){
        let formid =  /^[a-z]+[a-z0-9]{5,19}$/g;
        return formid.test(id);
    }
