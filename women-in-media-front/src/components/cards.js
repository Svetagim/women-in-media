import React from 'react';
import ReactCardFlip from 'react-card-flip';
import { Button } from 'react-bootstrap';
import Cloud from './Cloud'
import Mako from './Mako'
import WordsUse from './WordsUse'
class Cards extends React.Component {
    constructor() {
      super();
        this.state = {
        posts :[
       
        ],
        counter : 0,
        currPage : true
      };
      this.handleClick = this.handleClick.bind(this);
      this.eachPost = this.eachPost.bind(this);
      this.renderCards = this.renderCards.bind(this);
    }
   

    eachPost(post, i) {
        return (
            <div className="cards">
            <ReactCardFlip  key={`posts${i}`} index={i}
            isFlipped={this.state.posts[i].isFlipped} 
            flipSpeedBackToFront={0.9}
            flipSpeedFrontToBack={0.9}
        >
            <div style={styles.card}  
            onClick={() => this.handleClick(i)}
            >
                <img style={styles.image }
                alt=""
                src={post.img}
                />
            </div>

            <div style={styles.card} 
                onClick={() => this.handleClick(i)}
                >
                <h2>{post.header}</h2>
                <h3>{post.sub_header}</h3>
            </div>  
        </ReactCardFlip>
   
    </div>
        )
    }

    handleClick(i) {
        let flipped = this.state.posts[i].isFlipped;
        if(flipped)
            flipped=false;
        else
            flipped=true;
        
        // 1. Make a shallow copy of the items
        let posts = [...this.state.posts];
        let counter = this.state.counter
        // 2. Make a shallow copy of the item you want to mutate
        let post = {...posts[i]};
        // 3. Replace the property you're intested in
        if(post.isFlipped===false)
          counter++;
        post.isFlipped = flipped;
        
        // 4. Put it back into our array. N.B. we *are* mutating the array here, but that's why we made a copy first
        posts[i] = post;
        // 5. Set the state to our new copy
        this.setState({posts: posts,
                      counter: counter,
                      currPage : true
                      });
      
    }

  componentDidMount() {
      const url = 'https://women-in-media-back.herokuapp.com/';
      fetch(url)
          .then(res => res.json())
          .then(data => {
            let dataNew = [];
            for(let i = 0; i<data.posts.length; i++){
              let newObj = {
                words: data.posts[i].words,
                _id: data.posts[i]._id,
                source: data.posts[i].source,
                header: data.posts[i].header,
                sub_header: data.posts[i].sub_header,
                img: data.posts[i].img,
                gender: data.posts[i].gender,
                flag: data.posts[i].flag,
                isFlipped: false
              }
              dataNew.push(newObj);   
            }
            let newState = {
              mako: data.mako,
              flag: data.flag,
              female: data.female,
              posts: dataNew
            }
            this.setState(newState);
          })
          .catch(err => console.error(err));
  }
  renderCards(){
    return (
      <div style={styles.container}>
         <h1>?האם בכתבות המופיעות יש מסגור נשים</h1>
       { this.state.posts.map(this.eachPost)}
      <Button variant="secondary" style={styles.button} onClick={()=>this.setState({currPage:false})}>סיימתי</Button>

      </div>
    )
  }
  renderLongPage(){
    return(
      <div style={styles.rootStyle}>
        <h1> ניחשת {this.state.counter} תשובות נכונות </h1>
      <img src='https://miro.medium.com/max/1200/1*iwfaO6Z-zKUp1GkCfwlWKA.jpeg' alt="Logo" style={styles.center}/>
      <h1 className="myHeader">מסגור נשים בעיתונות, לא מה שחשבת.. או שכן</h1>
     <WordsUse style={styles.center}/>  
     <Cloud/>
     <Mako  style={styles.center}/>
     <h5 className="foot" ><a href="https://github.com/OnlpLab/yap" >של האוניברסיטה הפתוחה NLP של מעבדת ה API לצורך הכנת הפרויקט השתמשנו ב</a></h5>
     </div>
    )
  }
    render() {
      return this.state.currPage ? this.renderCards() : this.renderLongPage()
    }
  }

  const styles = {
    rootStyle: {
      backgroundColor: '#ffcf00',
        width: '100%',
        justifyContent: 'center'
    },
    card: {
      border: '1px solid #eeeeee',
      borderRadius: '3px',
      padding: '15px',
      width: '400px',
      height: '250px',
      margin: '10px',
      backgroundColor: 'white',
      textAlign: 'center'
    },
    image: {
      width: '370px',
      height: '220px',
    },
    container:{
      paddingTop: '60px',
      margin: '0 auto',
      width: '1300px',
      height: '1000px'
    },
    button:{
      width:'50%',
      display: 'flex',
      justifyContent: 'center',
      marginLeft: 'auto',
      marginRight: 'auto',
    },
    center:{
      display: 'block',
      marginLeft: 'auto',
      marginRight: 'auto',
    }
  
  };

  export default Cards