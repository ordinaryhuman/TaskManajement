// JavaScript Document

function Calendar(date, elementToDraw, onDateSelected, monthNames, dayNames) {
	//constants
	if(monthNames) this._monthNames = monthNames;
	else
		this._monthNames = [
			"January",
			"February",
			"March",
			"April",
			"May",
			"June",
			"July",
			"August",
			"September",
			"October",
			"November",
			"December",
		];
		
	if(dayNames) this._dayNames = dayNames;
	else
		this._dayNames = [
			"S", "M", "T", "W", "T", "F", "S"
		];
	
	//vars
	curDate 	= date;
	elmToDraw	= elementToDraw
	inst 		= "cal";
	this.isVisible = false;
	
	//Methods
	this.onDateSelected = onDateSelected;
	this.addYear = function(it) {
		var d = curDate;
		curDate = new Date(d.getFullYear() + it, d.getMonth(), 1);
	}
	
	this.addMonth = function(it) {
		var d = curDate;
		curDate = new Date(d.getFullYear(), d.getMonth() + it, 1);
	}
	
	this.hideCalendar = function() {
		this.isVisible = false;
		elmToDraw.innerHTML = "";
	}
	
	this.drawCalendar = function(date) {
		date = curDate = date || curDate;
		var s = "";
		this.isVisible = true;
		
		//Cal Headers
		s += '<table class="cal_header">';
		
		s += '<tr> ';
		s += '<td><a href="#" onclick="' + inst + '.addYear(-10);' + inst + '.drawCalendar(); return false;"><<</a></td>';
		s += '<td><a href="#" onclick="' + inst + '.addYear(-1);' + inst + '.drawCalendar(); return false;"><</a></td>';
		s += '<td class="cal_header_info">' + date.getFullYear() + '</td>';
		s += '<td><a href="#" onclick="' + inst + '.addYear( 1);' + inst + '.drawCalendar(); return false;">></a></td>';
		s += '<td><a href="#" onclick="' + inst + '.addYear( 10);' + inst + '.drawCalendar(); return false;">>></a></td>';
		s += '</tr>';
		
		s += '<tr> ';
		s += '<td><a href="#" onclick="' + inst + '.addMonth(-6);' + inst + '.drawCalendar(); return false;"><<</a></td>';
		s += '<td><a href="#" onclick="' + inst + '.addMonth(-1);' + inst + '.drawCalendar(); return false;"><</a></td>';
		s += '<td class="cal_header_info">' + this._monthNames[date.getMonth()] + '</td>';
		s += '<td><a href="#" onclick="' + inst + '.addMonth( 1);' + inst + '.drawCalendar(); return false;">></a></td>';
		s += '<td><a href="#" onclick="' + inst + '.addMonth( 6);' + inst + '.drawCalendar(); return false;">>></a></td>';
		s += '</tr>';
		
		s += "</table>";
		
		//Content header
		s += '<table class="cal_content">';
		for(var y = 0; y < 1; ++y) {
			s += '<tr class="cal_day_row">';
			for(var x = 0; x < 7; ++x) {
				s += '<td id="cal_' + x + '_' + y + '">';
				s += this._dayNames[x];
				s += '</td>';
			}
			s += "</tr>";
		}
		
		//Content
		var startIdx 	= new Date(date.getFullYear(), date.getMonth(), 1).getDay();
		var endIdx 		= 32 - new Date(date.getFullYear(), date.getMonth(), 32).getDate() + startIdx;
		var it = 0;
		for(var y = 1; y < 7; ++y) {
			s += "<tr>";
			for(var x = 0; x < 7; ++x) {
				s += '<td id="cal_' + x + '_' + y + '">';
				if(it >= startIdx && it < endIdx) {
					var curr = (it - startIdx + 1);
					s += '<a href="#" class="cal_date" onclick="'+inst+'.onDateSelected(new Date(' + date.getFullYear() + ',' + date.getMonth() + ',' + curr + '));  return false;">' + curr + '</a>';
				}
				s += '</td>';
				++it;
			}
			s += "</tr>";
		}
		s += "</table>";
		
		//Draw it
		elmToDraw.innerHTML = (s);
	}
}