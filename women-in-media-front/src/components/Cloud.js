import React, { Component } from "react";
import randomColor from "randomcolor";
import TagCloud from "react-tag-cloud";

class Cloud extends Component {

  constructor() {
    super();
    this.state = {
      female: "",
      flag: "",
      mako: "",
      words: ""
      };
      this.showCloud = this.showCloud.bind(this);
  }

  showCloud(){
    let dataRaw = []
      let keys = Object.keys(this.state.words);
      for(let i=0; i<keys.length; i++){
        let key = keys[i];
        dataRaw.push([key, this.state.words[key]])
      }
 
       return (
            <div className="app-outer" >
              <div className="app-inner">
                <TagCloud
                  className="tag-cloud"
                  rotate={() => Math.round(Math.random()) * 90}
                  style={{
                    fontFamily: "sans-serif",
                    fontSize: 30,
                    color: () =>
                      randomColor({
                        hue: "pink"
                      }),
                    padding: 5,
                    backgroundColor: 'white',
                  }}
                 
                >
                   {dataRaw.map((value, index) => {
                        return (
                          <div className="tag-item-wrapper">
                           <div style={{ fontSize: value[1]*7 }}>{value[0]}</div>
                          <div className="tag-item-tooltip">{value[1]}</div>
                        </div>
                        )
                      })}
            </TagCloud> 
        </div>
      </div>
      )
  }

  // eslint-disable-next-line no-dupe-class-members
  componentDidMount() {
    setInterval(() => {
      this.forceUpdate();
    }, 10000);
    const url = 'https://women-in-media-back.herokuapp.com/';
    fetch(url)
        .then(res => res.json())
        .then(data => {
          let dataNew = {
            female: data.female,
            flag: data.flag,
            mako: data.mako,
            words: data.words
          }
          this.setState(dataNew);
        })
        .catch(err => console.error(err));
}
  render() {
    return (
      this.showCloud()
    );
  }
}

export default Cloud;