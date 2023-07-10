$(document).ready(function() {
    var WorkDayModel = Backbone.Model.extend({
        urlRoot: '/save',

        defaults: {
            count: '',
            date: '',
            distance: '2 × 8 = 16 km',
            pay: '16 km × 30 Ft = 480 Ft',
            calcDate: ''
        }

    });

    var MyView = Backbone.View.extend({
        el: '#my-form',

        events: {
            'submit': 'handleSubmit'
        },

        initialize: function() {
            this.model = new WorkDayModel();
        },

        handleSubmit: function(event) {
            event.preventDefault();

            const dateString = this.$('#date').val();
            const dateArray = dateString.split(", ");

            let calcDate = this.$("#calcDate").val();
            let cYear = calcDate.slice(0,4);
            let cMonth = calcDate.slice(5,8);

            for (let i = 0; i < dateArray.length; i++) {
                const currentValue = this.model.get("count") || 0;
                if (dateArray[i].length == 1) {
                    this.model.set("date", cYear+"."+cMonth+".0"+dateArray[i]+".");
                } else {
                    this.model.set("date", cYear+"."+cMonth+"."+dateArray[i]+".");
                }
                if (currentValue < dateArray.length) {
                    this.model.set("count", currentValue + 1);
                } else {
                    this.model.set("count", 1);
                }
                this.model.save(null);
                this.$('#secondBtn').prop('disabled', true); // Letiltja a gombot a mentés után
            }
        }
    });

    var myView = new MyView();


    // ELSŐ RÉSZ LEZÁRVA

    var TmpInfoModel = Backbone.Model.extend({
            urlRoot: '/save-tmp',

            defaults: {
                sumMoney: '',
                personName: '',
                address: '',
                nowYearDate: '',
                nowMonthDate: '',
                nowDayDate: ''
            }

        });

        var MyViewV2 = Backbone.View.extend({
            el: '#my-form-2',

            events: {
                'submit': 'handleSubmit'
            },

            initialize: function() {
                this.model = new TmpInfoModel();
            },

            handleSubmit: function(event) {
                event.preventDefault();

                let d = new Date();
                let yearD = d.getFullYear();
                let monthD = d.getMonth() + 1;
                let dayD = d.getDate();

                if (monthD < 10) {
                    monthD = "0" + monthD;
                };
                if (dayD < 10) {
                    dayD = "0" + dayD;
                };

                this.model.set({
                    sumMoney: this.$('#sumMoney').val(),
                    personName: this.$('#personName').val(),
                    address: this.$('#address').val(),
                    nowYearDate: yearD,
                    nowMonthDate: monthD,
                    nowDayDate: dayD
                });

                this.model.save(null, {
                    success: function(model, response) {
                        console.log('Saved successfully:', response);
                        this.$('#firstBtn').prop('disabled', true); // Letiltja a gombot a mentés után
                        // Itt további műveleteket végezhetsz a mentés után
                    },
                    error: function(model, response) {
                        console.error('Error saving:', response);
                        this.$('#firstBtn').prop('disabled', true); // Letiltja a gombot a mentés után
                        // Itt kezelve az esetleges hibaüzeneteket
                    }
                });
            }
        });

        var myViewV2 = new MyViewV2();

});
