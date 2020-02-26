import { Chart } from "react-google-charts";
import React from 'react';

class Mako extends React.Component {
  constructor() {
    super();
      this.state = {
        female: "",
        flag: "",
        mako: "",
        words: ""
    };
    this.showChart = this.showChart.bind(this);
  }
 
  showChart(){
    let dataRaw = []
    dataRaw.push([
      'Element',
      'כמות מופעים',
      { role: 'style' },
      {
        sourceColumn: 0,
        role: 'annotation',
        type: 'string',
        calc: 'stringify',
      },
    ])
    let myData =[]
    let keys = Object.keys(this.state.words);
    for(let i=0; i<keys.length; i++){
      let key = keys[i];
      myData.push([key, this.state.words[key], '#ffcf00', null])
    }
    let sortedArray = myData.sort(function(a, b) {
      return b[1] - a[1];
    });
    console.log(sortedArray)

    dataRaw = dataRaw.concat(sortedArray)
    return(
      <div  className="mako">
      <Chart
    width={'500px'}
    height={'800px'}
    chartType="BarChart"
    loader={<div>Loading Chart</div>}
    data={dataRaw}

    options={{
      title: 'התפלגות השימוש במילים ממסגרות במאקו',
      width: 600,
      height: 800,
      bar: { groupWidth: '95%' },
      legend: { position: 'none' },
    }}
    // For tests
    rootProps={{ 'data-testid': '6' }}
  />
  </div>
)}
 
componentDidMount() {
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
    return (this.showChart())
  }
}

export default Mako