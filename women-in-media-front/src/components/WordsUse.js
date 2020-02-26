import { Chart } from "react-google-charts";
import React from 'react';

class WordsUse extends React.Component {
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
    let dataRaw=[
        ['תיאור', 'כמות כתבות',{ role: 'style' },
        {
          sourceColumn: 0,
          role: 'annotation',
          type: 'string',
          calc: 'stringify',
        }],
        ['סך הכל כתבות', this.state.mako, '#ffcf00', null],
        ['מתוכן הנושא אישה', this.state.female, '#ffcf00', null],
        ['מתוכן שימוש במילים ממסגרות', this.state.flag, '#ffcf00', null],
    ]
    return(
      <div className="box"
      >
      <Chart
    width={'500px'}
    height={'300px'}
    chartType="BarChart"
    loader={<div>Loading Chart</div>}
    data={dataRaw}

    options={{
        title: 'התפלגות הכתבות',
        width: 600,
        height: 400,
        bar: { groupWidth: '80%' },
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

export default WordsUse