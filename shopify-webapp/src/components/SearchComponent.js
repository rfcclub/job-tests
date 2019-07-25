import React, { Component } from 'react'
import PropTypes from 'prop-types'
import SearchBar from './SearchBar';
import ResultList from './ResultList';
import {connect} from 'react-redux';
import * as actions from '../actions/AppAction';

class SearchComponent extends React.Component {
  constructor({dispatch}) {
    super();
    this.dispatch = dispatch;
    dispatch(actions.getFavorites());
  }
  render() {
    return (
      <div className="row mt-3">
        <div className="col-10 offset-1">
          <SearchBar />
          <ResultList />  
        </div>  
      </div>
    );
  }  
}
SearchComponent = connect() (SearchComponent);
export default SearchComponent;