/*Downloaded from https://www.codeseek.co/dxbykov/palette-devextreme-jquery-scheduler-context-menu-eMvpoe */
DevExpress.viz.currentTheme("generic.light");
$(function() {
    var createAppointment = function(e) {
        var scheduler = e.component,
            data = e.cellData;

        scheduler.showAppointmentPopup({
            startDate: data.startDate
        }, true);
    };

    var createRecurringAppointment = function(e) {
        var scheduler = e.component,
            data = e.cellData;

        scheduler.showAppointmentPopup({
            startDate: data.startDate,
            recurrenceRule: "FREQ=DAILY"
        }, true);
    };

    var groupCell = function(e) {
        var scheduler = e.component;

        if(scheduler.option("groups")) {
            scheduler.option("crossScrollingEnabled", false);
            scheduler.option("groups", undefined);
        } else {
            scheduler.option("crossScrollingEnabled", true);
            // scheduler.option("groups", ["roomId"]);
            scheduler.repaint();
        };
    };

    var showCurrentDate = function(e) {
        var scheduler = e.component;

        scheduler.option("currentDate", new Date());
    };

    var showAppointment = function(e) {
        var scheduler = e.component,
            itemData = e.appointmentData;

        scheduler.showAppointmentPopup(itemData);
    };

    var deleteAppointment = function(e) {
        var scheduler = e.component,
            itemData = e.appointmentData;

        scheduler.deleteAppointment(itemData);
    };

    var repeatAppointmentWeekly = function(e) {
        var scheduler = e.component,
            itemData = e.appointmentData,
            targetedAppointmentData = e.targetedAppointmentData;

        scheduler.updateAppointment(itemData, $.extend(itemData, {
            startDate: targetedAppointmentData.startDate,
            recurrenceRule: "FREQ=WEEKLY"
        }));
    };

    var setResource = function(e, clickEvent) {
        var scheduler = e.component,
            itemData = e.appointmentData;

        scheduler.updateAppointment(itemData, $.extend(itemData, {
            //roomId: [clickEvent.itemData.id]
        }));
    };

    var cellContextMenuItems = [
        { text: 'New Appointment', onItemClick: createAppointment },
        { text: 'New Recurring Appointment', onItemClick: createRecurringAppointment },
        { text: 'Group by Room/Ungroup', beginGroup: true, onItemClick: groupCell },
        { text: 'Go to Today', onItemClick: showCurrentDate }
    ];

    var appointmentContextMenuItems = [
        { text: 'Open', onItemClick: showAppointment },
        { text: 'Delete', onItemClick: deleteAppointment },
        { text: 'Repeat Weekly', beginGroup: true, onItemClick: repeatAppointmentWeekly },
        // { text: 'Set Room', beginGroup: true }
    ];

    var getAppointmentMenuTemplate = function(itemData) {

        var template = $('<div></div>');

        if(itemData.icon) {
            template.append('<span class="' + itemData.icon + '"><span>');
        }

        if(itemData.items) {
            template.append('<span class="dx-icon-spinright"><span>');
        }

        if(itemData.color) {
            template.append("<div class='appointment-badge' style='background-color:" + itemData.color + ";'></div>");
        }

        template.append(itemData.text);

        if(itemData.text === "New Appointment until the end of the week") {
            template.append('<hr />');
        }

        return template;
    };

    var getCellMenuTemplate = function (itemData) {
        var template = $('<div></div>');

        if(itemData.icon) {
            template.append('<span class="' + itemData.icon + '"><span>');
        }
        template.append(itemData.text);

        return template;
    };

    // var roomsData = resourcesData;
    // $.each(roomsData, function (i, item) {
    //     item.onItemClick = setResource;
    // })

    // appointmentContextMenuItems = $.merge(appointmentContextMenuItems, roomsData);

    $("#scheduler").dxScheduler({
        dataSource: data,
        views: ["day", "month"],
        currentView: "month",
        currentDate: new Date(),
        firstDayOfWeek: 1,
        startDayHour: 9,
        groups: undefined,
        recurrenceEditMode: "series",
        onAppointmentContextMenu: function (e) {
            var itemData = e.appointmentData,
                contextMenuEvent = e;

            $("#appointment-context-menu").dxContextMenu({
                dataSource: appointmentContextMenuItems,
                width: 200,
                itemTemplate: function(itemData) {
                    var template = getAppointmentMenuTemplate(itemData);
                    return template;
                },
                target: e.appointmentElement,
                onItemClick: function(e) {
                    if(!e.itemData.items && e.itemData.onItemClick) {
                        e.itemData.onItemClick(contextMenuEvent, e);
                    }
                }
            });

        },
        onCellContextMenu: function(e) {
            e.event.stopPropagation();
            e.event.preventDefault();
            var contextMenuEvent = e,
                itemData = e.cellData;

            $("#context-menu").dxContextMenu({
                dataSource: cellContextMenuItems,
                itemTemplate: function(itemData) {
                    var template = getCellMenuTemplate(itemData);

                    return template;
                },
                onItemClick: function(e) {
                    e.itemData.onItemClick(contextMenuEvent);
                },
                width: 200,
                visible: true,
                target: e.cellElement
            });

            return false;
        },
        // resources: [{
        //     fieldExpr: "roomId",
        //     dataSource: resourcesData,
        //     label: "Room"
        // }],
        height: 600
    });
});

var data = [
    {
        // text: "Watercolor Landscape",
        // //roomId: [1],
        startDate: new Date(2017, 4, 1, 9, 30),
        endDate: new Date(2017, 4, 1, 11),
        // recurrenceRule: "FREQ=WEEKLY;BYDAY=TU,FR;COUNT=10"
    }, {
        // text: "Oil Painting for Beginners",
        //roomId: [2],
        startDate: new Date(2017, 4, 3, 9, 30),
        endDate: new Date(2017, 4, 3, 11)
    }, {
        // text: "Testing",
        //roomId: [3],
        startDate: new Date(2017, 4, 1, 12, 0),
        endDate: new Date(2017, 4, 1, 13, 0),
        // recurrenceRule: "FREQ=WEEKLY;BYDAY=MO;WKST=TU;INTERVAL=2;COUNT=2"
    }, {
        // text: "Meeting of Instructors",
        //roomId: [4],
        startDate: new Date(2017, 4, 1, 9, 0),
        endDate: new Date(2017, 4, 1, 9, 15),
        // recurrenceRule: "FREQ=DAILY;BYDAY=WE;"
    }, {
        // text: "Recruiting students",
        //roomId: [5],
        startDate: new Date(2017, 4, 26, 10, 0),
        endDate: new Date(2017, 4, 26, 11, 0),
        // recurrenceRule: "FREQ=YEARLY;BYWEEKNO=23",
        // recurrenceException: "20170611T100000"
    }, {
        // text: "Final exams",
        //roomId: [3],
        startDate: new Date(2017, 4, 26, 12, 0),
        endDate: new Date(2017, 4, 26, 13, 35),
        // recurrenceRule: "FREQ=YEARLY;BYWEEKNO=24;BYDAY=TH,FR"
    }, {
        // text: "Monthly Planning",
        //roomId: [4],
        startDate: new Date(2017, 4, 26, 14, 30),
        endDate: new Date(2017, 4, 26, 15, 45),
        // recurrenceRule: "FREQ=MONTHLY;BYMONTHDAY=27"
    }, {
        // text: "Open Day",
        //roomId: [5],
        startDate: new Date(2017, 4, 1, 9, 30),
        endDate: new Date(2017, 4, 1, 13),
        // recurrenceRule: "FREQ=YEARLY;BYYEARDAY=148"
    }
];

var resourcesData = [
    {
        // text: "Room 101",
        id: 1,
        color: "#bbd806"
    }, {
        // text: "Room 102",
        id: 2,
        color: "#f34c8a"
    }, {
        // text: "Room 103",
        id: 3,
        color: "#ae7fcc"
    }, {
        // text: "Meeting room",
        id: 4,
        color: "#ff8817"
    }, {
        // text: "Conference hall",
        id: 5,
        color: "#03bb92"
    }
];