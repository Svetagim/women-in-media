const mongoose = require('mongoose');
const Posts = require('../model/posts'); // access the MODEL
// for route /final-ideas/getAllIdeas
exports.getData = (req, res) => {
    Posts.find({})
    .then(docs => {        
        let words = [];
        let female = 0;
        let mako = 0;
        let flag = 0;
        let posts = []
        let posts9 = []
        for(let i=0; i<docs.length; i++){
            if(docs[i].source==='Mako'){
                mako++;
                if(docs[i].gender==='F'){
                    female++;
                    if(docs[i].flag==='1'){
                        flag++; 
                        posts.push(docs[i])
                        for(let j=0; j<docs[i].words.length; j++)
                            words.push(docs[i].words[j]);
                    } 
                }
            }
        }
        for(let j=0; j<9; j++)
            posts9.push(posts[Math.floor(Math.random() * (posts.length))]);
        let counts = {};
            words.forEach(el => counts[el] = 1  + (counts[el] || 0))
        data = {
            posts: posts9,
            female: female,
            flag: flag,
            mako: mako,
            words: counts
        }

        return res.json(data);
 })
 .catch(err => console.log(`query error: ${err}`))
};