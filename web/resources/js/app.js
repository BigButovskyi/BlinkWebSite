$(function () {

    var service = null;
    var email;
    var $clientName = $('#client-name');
    var $clientPhone = $('#client-phone');
    var $emailSubmitButton = $('#email_submit');
    var $idClientCode = $('#id-client-code');
    var $codeSubmitButton = $('#code_submit');
    var $submitService = $('#submit-service');
    var emailEdit;
    var $emailSubmitEdit = $('#email_submit-edit');
    var $idClientCodeEdit = $('#id-client-code-edit');
    var $codeSubmitEdit = $('#code_submit-edit');
    var $confirmDate = $('#confirm-date');

    var codeIsChecked = false;

    $(document).ready(function () {
        $idClientCode.val("");
        $clientName.val("");
        $clientPhone.val("");
    });

    //DATE-TIME-PICKER

    var allowTime = [
        '9:00:00',
        '10:00:00',
        '11:00:00',
        '12:00:00',
        '13:00:00',
        '14:00:00',
        '15:00:00',
        '16:00:00',
        '17:00:00',
        '18:00:00',
        '19:00:00',
        '20:00:00',
        '21:00:00'];

    function setTime(allow, disable) {
        if (disable.length == 0)
            return allow;
        disable.forEach(function (value) {
            var index = allow.indexOf(value);
            if (index > -1) {
                allow.splice(index, 1);
            }
        });
        return allow;
    }

    // var logic = function (currentDateTime) {
    //     // 'this' is jquery object datetimepicker
    //     if (currentDateTime.getDay() == 6 || currentDateTime.getDay() == 7) {
    //         this.setOptions({
    //             allowTimes: allowTimeWeekend
    //         });
    //     } else
    //         this.setOptions({
    //             allowTimes: allowTimeWorking
    //         });
    // };

    var $filterDate = $('#filter-date');
    var $filterTime = $('#filter-time');

    $filterDate.datetimepicker({
        // onChangeDateTime: logic,
        // onShow: logic,
        dayOfWeekStart: 1,
        minDate: '+1970/01/01',
        maxDate: '+1970/02/02',
        timepicker: false,
        format: 'Y-m-d'
    });

    //DATE-TIME-PICKER END!
    //////////////////////////////////////////////////////////////////////////////////////
    // GOOGLE MAPS

    function initialize() {

//Тут починаємо працювати з картою
        var point = {lat: 50.450083, lng: 30.495375};
        home = new google.maps.LatLng(point);

        var mapProp = {
            center: home,
            zoom: 16
        };
        var html_element = document.getElementById("googleMap");
        mapp = new google.maps.Map(html_element, mapProp);

        var marker = new google.maps.Marker({
            position: point,
            map: mapp,
            title: "We are here!"
        });

//Карта створена і показана
    }

//Коли сторінка завантажилась
    google.maps.event.addDomListener(window, 'load', initialize);

    // GOOGLE MAPS END!
    /////////////////////////////////////////////////////////////////////////////////////////////
    // SCROLL BUTTONS

    $('.about-click').click(function () {
        $('html,body').animate({
                scrollTop: $('.about-us').offset().top - 90
            },
            'slow');
    });

    $('.services-click').click(function () {
        $('html,body').animate({
                scrollTop: $('.services').offset().top - 90
            },
            'slow');
    });

    $('.gallery-click').click(function () {
        $('html,body').animate({
                scrollTop: $('.gallery').offset().top - 90
            },
            'slow');
    });

    $('.contacts-click').click(function () {
        $('html,body').animate({
                scrollTop: $('.contacts').offset().top - 90
            },
            'slow');
    });

    $('.book-click').click(function () {
        $('html,body').animate({
                scrollTop: $('.book-now').offset().top - 90
            },
            'slow');
    });

    // SCROLL BUTTONS END!
    ///////////////////////////////////////////////////////////////////////////////////////////
    // API

    //
    // function backendPost(url, data) {
    //     $.ajax({
    //         url: url,
    //         type: 'POST',
    //         method: "post",
    //         contentType: 'application/json',
    //         data: JSON.stringify(data),
    //         success: function (data) {
    //             console.log("success post");
    //             // callback(null, data);
    //         },
    //         error: function () {
    //             // callback(new Error("Ajax Failed"));
    //             console.log("error post");
    //         }
    //     })
    // }

    // API END!
    ////////////////////////////////////////////////////////////////////////
    // BOOKING

    $('#nails-service, #brows-service, #makeup-service').click(function () {
        var $brows = $('#brows-service');
        var $nails = $('#nails-service');
        var $makeUp = $('#makeup-service');

        if (this.id == 'nails-service') {
            service = "Nails";
            $nails.addClass("isChosen");
            $brows.removeClass("isChosen");
            $makeUp.removeClass("isChosen");
        }
        else if (this.id == 'brows-service') {
            service = "Brows";
            $brows.addClass("isChosen");
            $nails.removeClass("isChosen");
            $makeUp.removeClass("isChosen");
        }
        else if (this.id == 'makeup-service') {
            service = "MakeUp";
            $makeUp.addClass("isChosen");
            $nails.removeClass("isChosen");
            $brows.removeClass("isChosen");
        }
        //console.log(service);
    });

    function validateMail(mail) {
        email = mail;
        var data = {email: mail};
        $.ajax({
            url: '/emailVerification',
            type: 'POST',
            method: "post",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data) {
                if (data == true) {
                    alert("Please, check your email!")
                    $idClientCode.removeAttr('disabled');
                    $codeSubmitButton.removeAttr('disabled');
                    // console.log("mail validated in post method");
                } else {
                    alert("Your email is incorrect. Please try again.");
                }
            },
            error: function () {
                alert("An error has occurred. Please try again.");
            }
        });
    }

    function checkCode(code) {
        var data = {
            email: email,
            code: code
        };
        $.ajax({
            url: '/finalVerification',
            type: 'POST',
            method: "post",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data) {
                if (data == true) {
                    $filterDate.removeAttr('disabled');
                    $confirmDate.removeAttr('disabled');
                    // $submitService.removeAttr('disabled');
                    // $clientName.removeAttr('disabled');
                    // $clientPhone.removeAttr('disabled');
                } else {
                    alert("You have entered the wrong code. Please try again.");
                }
            },
            error: function () {
                //console.log("error post");
                alert("An error has occurred. Please try again.");
            }
        });
    }

    function bookClient(name, phone, date, time) {
        var client = {
            phone: phone,
            email: email,
            name: name
        };
        var data = {
            client: client,
            service: service,
            date: date,
            time: time
        };
        $.ajax({
            url: '/registration/addClientAndReservations',
            type: 'POST',
            method: "post",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data) {
                alert("We are waiting forward to see you!");
            },
            error: function () {
                alert("An error has occurred. Please try again.");
            }
        });
    }

    $emailSubmitButton.click(function () {
        var mail = $('#client-email').val();
        if (mail.length > 0) {
            validateMail(mail);
            // $idClientCode.removeAttr('disabled');
            // $codeSubmitButton.removeAttr('disabled');
            // console.log("mail validated");
        }
    });

    $codeSubmitButton.click(function () {
        var code = $idClientCode.val();
        if (code.length == 4) {
            checkCode(code);
            // $filterDate.removeAttr('disabled');
            // $confirmDate.removeAttr('disabled');
        }
        else {
            alert("Invalid code. Please try again.");
        }
    });

    $confirmDate.click(function () {
            if (service == null) {
                alert("Please choose the service!");
            } else {
                var date = $filterDate.val();
                var data = {
                    email: email,
                    service: service,
                    date: date
                };

                // $filterTime.removeAttr('disabled');
                // $clientName.removeAttr('disabled');
                // $clientPhone.removeAttr('disabled');
                // $submitService.removeAttr('disabled');

                $.ajax({
                        url: '/registration/getBusyTimesForService ',
                        type: 'POST',
                        method: "post",
                        contentType: 'application/json',
                        data: JSON.stringify(data),
                        success: function (data) {
                            $filterTime.removeAttr('disabled');

                            // var logic = function (currentDateTime) {
                            //     // 'this' is jquery object datetimepicker
                            //     if (currentDateTime.getDay() == 6 || currentDateTime.getDay() == 7) {
                            //         this.setOptions({
                            //             allowTimes: setTime(allowTimeWeekend, data)
                            //         });
                            //     } else
                            //         this.setOptions({
                            //             allowTimes: setTime(allowTime, data)
                            //         });
                            // };

                            var allow = allowTime.slice();
                            $filterTime.datetimepicker({
                                allowTimes: setTime(allow, data),
                                datepicker: false,
                                format: 'H:i'
                            });


                            $clientName.removeAttr('disabled');
                            $clientPhone.removeAttr('disabled');
                            $submitService.removeAttr('disabled');
                        },
                        error: function () {
                            alert("An error has occurred. Please try again.");
                        }
                    }
                );


                $submitService.removeAttr('disabled');
                $clientName.removeAttr('disabled');
                $clientPhone.removeAttr('disabled');
            }
        }
    )
    ;

    // $submitService.click(function () {
    //     var name = $clientName.val();
    //     // $("#welcome-name").text(name);
    //     var date = $filterDate.val();
    //     // $('#welcome-date').text(date);
    //     var phone = $clientPhone.val();
    //     var time = $filterTime.val();
    //     // $('#welcome-time').text(time);
    //     if (name.length !== 0 && date.length !== 0 && time.length !== 0 && service !== null) {
    //         bookClient(name, phone, date, time);
    //     }
    //     else {
    //         alert("Something is missing...");
    //     }
    // });

    $submitService.click(function () {
        var name = $clientName.val();
        // $("#welcome-name").text(name);
        var date = $filterDate.val();
        // $('#welcome-date').text(date);
        var phone = $clientPhone.val();
        var time = $filterTime.val();
        // $('#welcome-time').text(time);
        var $checkPhone = checkPhone(phone);

        console.log($checkPhone);
        if (name.length !== 0 && date.length !== 0 && time.length !== 0 && service !== null) {
            if ($checkPhone == false) {
                alert("The format of your phone number is incorrect!");
            }
            else {
                bookClient(name, phone, date, time);
            }
        }
        else {
            alert("Something is missing...");
        }
    });

    function checkPhone(phone) {
        var res = false;
        var phoneReg = /^[0-9+]/;
        var update;
        if (phoneReg.test(phone) && phone !== "") {
            if (phone.startsWith("+380")) {
                update = phone.replace("+380", "");
                if (update.length == 9)
                    res = true;
            }
            else if (phone.startsWith("0")) {
                update = phone.replace("0", "");
                if (update.length == 9)
                    res = true;
            }
            else if (phone.startsWith("380")) {
                update = phone.replace("380", "");
                if (update.length == 9)
                    res = true;
            }
        }
        return res;

    }

    // BOOKING END!
    /////////////////////////////////////////////
    // EDIT


    $emailSubmitEdit.click(function () {
        var mail = $('#client-email-edit').val();
        if (mail.length > 0) {
            validateMailEdit(mail);
            email = mail;
            //console.log("mail validated");
            // getBookings(null);
        }
    });

    function getBookings(data) {
        $('#booking-list').html('');
        // data = {
        //     "makeUp": [
        //         {
        //             "date": "2010-01-13",
        //             "time": "22:30:00"
        //         },
        //         {
        //             "date": "2000-01-15",
        //             "time": "07:00:00"
        //         }
        //     ],
        //     "brows": [],
        //     "nails": [
        //         {
        //             "date": "1997-01-14",
        //             "time": "14:30:00"
        //         },
        //         {
        //             "date": "2018-04-25",
        //             "time": "15:00:00"
        //         },
        //         {
        //             "date": "2010-01-13",
        //             "time": "22:30:00"
        //         }
        //     ]
        // };
        if (data.makeUp != undefined) {
            data.makeUp.forEach(function (value) {
                var ser = "Make Up";
                var date = value.date;
                var time = value.time.slice(0, 5);
                var space = " ";
                $('#booking-list').append("<div class=\"one-reservation\">\n" +
                    "                        <div class=\"edit-options\">\n" +
                    "                            <div class=\"service-ed\">" + ser + "</div>\n" +
                    "                            <div class=\"data-edit\">" + date + space + time + "</div>\n" +
                    "                            <div class=\"edit-icons\">\n" +
                    "                                <button class=\"ed-icon\"><i class=\"step fi-pencil size-21\"></i></button>\n" +
                    "                                <button class=\"del-icon\"><i class=\"step fi-x size-21\"></i></button>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"new-edit is-hidden\">\n" +
                    "                            <div class=\"menu menu-date-check date-input-box\">\n" +
                    "<span class=\"input-with-but\">\n" +
                    "                                <input class=\"login-box-input date-input date-input-edit\" type=\"text\"\n" +
                    "                                       id=\"filter-date-edit\"\n" +
                    "                                       placeholder=\"Click to choose date\"/>\n" +
                    "                                    <button id=\"confirm-date-edit\"><i class=\"fi-check\"></i></button>\n" +
                    "                                </span>" +
                    "<span class=\"input-with-but\">\n" +
                    "                                <input class=\"login-box-input date-input date-input-edit\" type=\"text\"\n" +
                    "                                       id=\"filter-time-edit\"\n" +
                    "                                       placeholder=\"Click to choose time\" disabled/>\n" +
                    "                                    <button id=\"confirm-time-edit\" disabled><i class=\"fi-check\"></i></button>\n" +
                    "                                </span>" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>");
            });
            data.brows.forEach(function (value) {
                var ser = "Brows";
                var date = value.date;
                var time = value.time.slice(0, 5);
                var space = " ";
                $('#booking-list').append("<div class=\"one-reservation\">\n" +
                    "                        <div class=\"edit-options\">\n" +
                    "                            <div class=\"service-ed\">" + ser + "</div>\n" +
                    "                            <div class=\"data-edit\">" + date + space + time + "</div>\n" +
                    "                            <div class=\"edit-icons\">\n" +
                    "                                <button class=\"ed-icon\"><i class=\"step fi-pencil size-21\"></i></button>\n" +
                    "                                <button class=\"del-icon\"><i class=\"step fi-x size-21\"></i></button>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"new-edit is-hidden\">\n" +
                    "                            <div class=\"menu menu-date-check date-input-box\">\n" +
                    "<span class=\"input-with-but\">\n" +
                    "                                <input class=\"login-box-input date-input date-input-edit\" type=\"text\"\n" +
                    "                                       id=\"filter-date-edit\"\n" +
                    "                                       placeholder=\"Click to choose date\"/>\n" +
                    "                                    <button id=\"confirm-date-edit\"><i class=\"fi-check\"></i></button>\n" +
                    "                                </span>" +
                    "<span class=\"input-with-but\">\n" +
                    "                                <input class=\"login-box-input date-input date-input-edit\" type=\"text\"\n" +
                    "                                       id=\"filter-time-edit\"\n" +
                    "                                       placeholder=\"Click to choose time\" disabled/>\n" +
                    "                                    <button id=\"confirm-time-edit\" disabled><i class=\"fi-check\"></i></button>\n" +
                    "                                </span>" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>");
            });
            data.nails.forEach(function (value) {
                var ser = "Nails";
                var date = value.date;
                var time = value.time.slice(0, 5);
                var space = " ";
                $('#booking-list').append("<div class=\"one-reservation\">\n" +
                    "                        <div class=\"edit-options\">\n" +
                    "                            <div class=\"service-ed\">" + ser + "</div>\n" +
                    "                            <div class=\"data-edit\">" + date + space + time + "</div>\n" +
                    "                            <div class=\"edit-icons\">\n" +
                    "                                <button class=\"ed-icon\"><i class=\"step fi-pencil size-21\"></i></button>\n" +
                    "                                <button class=\"del-icon\"><i class=\"step fi-x size-21\"></i></button>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"new-edit is-hidden\">\n" +
                    "                            <div class=\"menu menu-date-check date-input-box\">\n" +
                    "<span class=\"input-with-but\">\n" +
                    "                                <input class=\"login-box-input date-input date-input-edit\" type=\"text\"\n" +
                    "                                       id=\"filter-date-edit\"\n" +
                    "                                       placeholder=\"Click to choose date\"/>\n" +
                    "                                    <button id=\"confirm-date-edit\"><i class=\"fi-check\"></i></button>\n" +
                    "                                </span>" +
                    "<span class=\"input-with-but\">\n" +
                    "                                <input class=\"login-box-input date-input date-input-edit\" type=\"text\"\n" +
                    "                                       id=\"filter-time-edit\"\n" +
                    "                                       placeholder=\"Click to choose time\" disabled/>\n" +
                    "                                    <button id=\"confirm-time-edit\" disabled><i class=\"fi-check\"></i></button>\n" +
                    "                                </span>" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>");
            });
        }
    }


    function validateMailEdit(mail) {
        emailEdit = mail;
        var data = {email: mail};
        $.ajax({
            url: '/edit/getClientReservations',
            type: 'POST',
            method: "post",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data) {
                getBookings(data);
            },
            error: function () {
                alert("An error has occurred. Please try again.");
            }
        });
    }

    function deleteBooking(date, time, mail, service) {
        var data = {
            email: mail,
            service: service,
            date: date,
            time: time
        };
        $.ajax({
            url: '/delete/removeReservation',
            type: 'POST',
            method: "post",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data) {
            },
            error: function () {
                alert("An error has occurred. Please try again.");
            }
        });
    }

    function editBooking(date, time, mail, service, newDate, newTime) {
        var data = {
            email: mail,
            service: service,
            old_date: date,
            old_time: time,
            new_date: newDate,
            new_time: newTime
        };
        console.log(data);
        $.ajax({
            url: '/edit/updateClientReservation ',
            type: 'PUT',
            method: "put",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data) {
                alert("Your booking has been successfully changed!");
            },
            error: function () {
                alert("An error has occurred. Please try again.");
            }
        });
    }

    var $bookingList = $('#booking-list');


    $bookingList.on('click', '.ed-icon', function () {
        //var $newEdit = $(this).parent().parent().siblings('.new-edit');
        if ($(this).parent().parent().siblings('.new-edit').hasClass("is-hidden")) {

            $(this).parent().parent().siblings('.new-edit').removeClass("is-hidden");

            var service = $(this).parent().siblings('.service-ed').text();
            var date = $(this).parent().siblings('.data-edit').text();
            var oldDate = date.substr(0, 10);
            var oldTime = date.substr(11, 16);
            console.log("EDIT " + service + " " + oldDate + " " + oldTime);

            var $editDate = $(this).parent().parent().siblings('.new-edit').find("#filter-date-edit");
            var $editTime = $(this).parent().parent().siblings('.new-edit').find("#filter-time-edit");
            var $confirmTime = $(this).parent().parent().siblings('.new-edit').find('#confirm-time-edit');


            $editDate.datetimepicker({
                // onChangeDateTime: logic,
                // onShow: logic,
                minDate: '+1970/01/01',
                maxDate: '+1970/02/02',
                timepicker: false,
                format: 'Y/m/d'
            });

            var $checkBut = $(this).parent().parent().siblings('.new-edit').find('#confirm-date-edit');
            $checkBut.click(function () {
                    var data = {
                        email: email,
                        service: service,
                        date: date
                    };

                    // $editTime.removeAttr('disabled');
                    // $confirmTime.removeAttr('disabled');

                    $.ajax({
                            url: '/registration/getBusyTimesForService ',
                            type: 'POST',
                            method: "post",
                            contentType: 'application/json',
                            data: JSON.stringify(data),
                            success: function (data) {
                                $editTime.removeAttr('disabled');
                                $confirmTime.removeAttr('disabled');

                                // var logic = function (currentDateTime) {
                                //     // 'this' is jquery object datetimepicker
                                //     if (currentDateTime.getDay() == 6 || currentDateTime.getDay() == 7) {
                                //         this.setOptions({
                                //             allowTimes: setTime(allowTimeWeekend, data)
                                //         });
                                //     } else
                                //         this.setOptions({
                                //             allowTimes: setTime(allowTime, data)
                                //         });
                                // };

                                var allow = allowTime.slice();
                                $editTime.datetimepicker({
                                    allowTimes: setTime(allow, data),
                                    datepicker: false,
                                    format: 'H:i'
                                });
                            },
                            error: function () {
                                alert("An error has occurred. Please try again.");
                            }
                        }
                    );
                }
            );

            $confirmTime.click(function () {
                var newDate = $editDate.val();
                var newTime = $editTime.val();

                editBooking(oldDate, oldTime, emailEdit, service, newDate, newTime);
            });

        }
        else {
            $(this).parent().parent().siblings('.new-edit').addClass("is-hidden");
        }
    })
    ;

    $bookingList.on('click', '.del-icon', function () {
        var check = confirm("Are you sure, you want to delete these booking?");
        if (check == true) {
            var service = $(this).parent().siblings('.service-ed').text();
            var dateD = $(this).parent().siblings('.data-edit').text();
            var date = dateD.substr(0, 10);
            var time = dateD.substr(11, 16);
            console.log("DEL " + service + " " + date + " " + time);

            deleteBooking(date, time, emailEdit, service);
            $(this).parent().parent().parent().remove();
        } else {

        }

    });

// EDIT END!
////////////////////////////////////////


})
;