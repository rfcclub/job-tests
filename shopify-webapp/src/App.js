import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import SearchComponent from './components/SearchComponent';
import FavoriteList from './components/FavoriteList';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h4 className="App-title">My Github Favorites</h4>
        </header>
        <div className="container-fluid">
          <div className="row">
            <div className="col-6">
              <SearchComponent />
            </div>
            <div className="col-6 favorite-background">
              <FavoriteList />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default App;
