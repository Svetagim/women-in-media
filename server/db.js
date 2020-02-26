const mongoose = require('mongoose');
const consts = require('./consts');
mongoose
 .connect('mongodb://***:****@ds013320.mlab.com:13320/women_in_media')
 .then(() => console.log('connected'))
 .catch(err => console.log(`connection error: ${err}`));
