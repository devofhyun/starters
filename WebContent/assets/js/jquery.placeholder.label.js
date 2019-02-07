(function ($) {
    $.fn.placeholderLabel = function(options) {
                
        var settings = $.extend({
            // These are the defaults.
            placeholderColor: "#898989",
            labelColor: "#FFF",
            labelSize: this.css('font-size', '1rem'),
            labelWeight: this.css('font-weight'),
            fontStyle: "normal",
            useBorderColor: true,
            inInput: true,
            timeMove: 200
        }, options); 

        var BindOnData = function (label, input, pt){
            var lh = label.height();
            var mtm = Number(pt.replace('px','')) + (lh/2);
            if(!settings.inInput){
                mtm += lh/2;
                label.css('background-color','');
            }
            label.animate({
                marginTop: "-="+mtm,
                fontSize: settings.labelSize,
            }, settings.timeMove);
            input.keyup();
        }
        //Work
        $(this).each(function (i,e){
            var self = $(e);
            if(self.attr('bind-placeholder-label') != undefined){
                var pt = self.css('padding-top');
                BindOnData(self.prev(), self, pt);
            }
            var currentBorderColor = self.css('border-color');
            var currentPlaceholderSize = self.css('font-size', '19px');
            if(self.attr('placeholder')){
            
                var label = $('<label></label>');
                label.css('position','absolute');
                label.css('cursor','initial');
                label.css('font-style',settings.fontStyle);
                label.css('color',settings.placeholderColor);
                label.css('font-size', '1rem');
                label.css('font-weight','normal');
                

                var text = self.attr('placeholder');
                self.removeAttr('placeholder');
                label.text(text);
                var ep = self.position().left;
                var pt = self.css('padding-top');
                var pl = self.css('padding-left');
                var mt = self.css('margin-top');
                var ml = self.css('margin-left');

                label.css('margin-top', (Number(pt.replace('px',''))) + Number(mt.replace('px','')));
                label.css('margin-left', (Number(pl.replace('px','')) - 5) + Number(ml.replace('px','')));
                label.css('padding-left','5px');
                label.css('padding-right','5px');
                label.css('background-color','rgba(255, 255, 255, 0)');
                //Event
                var self = self;
                label.click(function (){
                    self.focus();
                });
                self.focus(function(){
                    if(settings.useBorderColor){
                        self.css('border-color',settings.labelColor);
                    }
                    var placeholder = "STARTERS"
                    	
                    label.css('color',settings.labelColor);
                    label.css('font-weight','bolder');
                    label.css('font-size', '60px');
                    label.text(placeholder);
                    if(!self.val().length){
                        var lh = label.height();
                        var mtm = Number(pt.replace('px','')) + (lh);
                        if(!settings.inInput){
                            mtm += (lh+3);
                            label.css('background-color','');
                        }
                        label.animate({
                            marginTop: "-="+mtm,
                            fontSize: settings.labelSize,
                            fontWeight: '900',
                        }, settings.timeMove);
                    }
                });
                self.blur(function(){
                    if(settings.useBorderColor){
                        self.css('border-color',currentBorderColor);
                    }
                    var text = "검색할 키워드를 입력해주세요.";
                    
                    label.css('color',settings.placeholderColor);
                    label.css('font-size', '1rem');
                    label.text(text);
                    if(!self.val().length){
                        var lh = label.height();
                        var mtm = Number(pt.replace('px','')) + (lh/2);
                        if(!settings.inInput){
                            mtm += (lh+3);
                            label.css('background-color','');
                        }
                        label.animate({
                            marginTop: '6px',
                            fontSize: currentPlaceholderSize,
                            fontWeight: settings.labelWeight,
                        }, settings.timeMove);
                    }
                });
                if(self.attr('alt')){
                    var textLabel = self.attr('alt');
                    var textLabelOld = label.text();
                    self.removeAttr('alt');
                    self.keyup(function (){
                        if(self.val().length){
                            label.text(textLabel);
                        } else {
                            label.text(textLabelOld);
                        }
                    });
                }
                 self.before(label);
                 if(self.val().length){
                    BindOnData(label, self, pt);
                }
                return self.attr('bind-placeholder-label','true');
            } else {
                return null;
            }
        });
    };
}(jQuery));
