var mongoose = require('mongoose'),
    posts     = new mongoose.Schema({
        source: String,
        header: String,
        sub_header: String,
        img: String,
        gender: String,
        words: [Object],
        flag: String
    });

    var Posts = mongoose.model('data2', posts);

    module.exports = Posts;