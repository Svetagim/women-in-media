import React from 'react';
import './App.css';
import Cards from './components/cards'
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  
  return (
    <div className="App" 
    style={styles.rootStyle}
    >
      <Cards style={styles.center}/>  
    </div>
  );
}

const styles = {
  rootStyle: {
    backgroundColor: '#ffcf00',
      width: '100%',
      justifyContent: 'center'
    },
  card: {
     display: 'flex',
     justifyContent: 'center',
  },
  center: {
    display: 'block',
    marginLeft: 'auto',
    marginRight: 'auto',
  },
};

export default App;
