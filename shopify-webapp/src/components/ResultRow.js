import React, { Component } from 'react'
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import * as actions from '../actions/AppAction';

class ResultRow extends Component {
  propTypes = {
    key: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    url: PropTypes.string.isRequired,
    language: PropTypes.string.isRequired,
    latestTag: PropTypes.string,
    hideAddButton: PropTypes.bool
  }
  constructor(props) {
    super(props);
    const { dispatch } = props;
    this.dispatch = dispatch;
    if (!props.latestTag || props.latestTag === '-') {
      dispatch(actions.getTag(props.name));
    }
  };
  addFavorite() {
    this.dispatch(actions.addFavorite(this.props.name));
  }
  render() {
    return (
      <div className="row mt-2">
        <div className="col-4 text-left">
          <a href={this.props.url}>{this.props.name}</a>
        </div>
        <div className="col-4 text-left">
          {this.props.language}
        </div>
        <div className="col-3 text-left">
          {this.props.latestTag}
        </div>
        <div className="col-1 text-right">
          {this.props.hideAddButton?(<div />):(<a href="#" onClick={this.addFavorite.bind(this)}>Add</a>)}
        </div>
      </div>
    );
  }
  
}

export default connect() (ResultRow);