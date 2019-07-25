import React, { Component } from 'react';
import * as action from '../actions/AppAction'
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import * as actions from '../actions/AppAction';

class SearchBar extends Component {

  constructor({dispatch}) {
    super();
    this.dispatch = dispatch;
  }
  searchRepositories() {
    var searchString = this.input.value;
    this.dispatch(actions.searchRepositories(searchString));
  }
  searchByEnter(event) { 
    if(event.charCode === 13) {
      this.searchRepositories();
    }
  }
  checkClearResult(event) {
    var searchString = this.input.value;
    if (searchString === '') {
      this.dispatch(actions.clearResult());
    }
  }
  render() {
    return (
      <div className="row">
        <div className="col-9">
          <input 
            type="text"
            ref={(input) => this.input = input}
            onKeyPress={this.searchByEnter.bind(this)}
            onChange={this.checkClearResult.bind(this)}
            className="form-control" /> 
        </div>
        <div className="col-3 nopadding">
            <button 
              onClick={this.searchRepositories.bind(this)} 
              className="btn btn-primary btn-shopify-background btn-block">Search</button> 
        </div>  
      </div>
    );
  }  
}
SearchBar = connect()(SearchBar);

export default SearchBar;