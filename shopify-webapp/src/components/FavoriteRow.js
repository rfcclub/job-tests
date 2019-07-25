import React, { Component } from 'react'
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import * as actions from '../actions/AppAction';

class FavoriteRow extends Component {
  constructor(props) {
    super(props);
    const { dispatch } = props;
    this.dispatch = dispatch;
  };
  removeFavorite() {
    this.dispatch(actions.removeFavorite(this.props.name));
  }
  propTypes = {
    key: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    url: PropTypes.string.isRequired,
    language: PropTypes.string.isRequired,
    latestTag: PropTypes.string
  }
  render() {
    return (
      <div className="row mt-2">
        <div className="col-4 text-left">
          <a href={this.props.url}>{this.props.name}</a>
        </div>
        <div className="col-3 text-left">
          {this.props.language}
        </div>
        <div className="col-3 text-left">
          {this.props.latestTag}
        </div>
        <div className="col-2 text-right">
          <a href="#" onClick={this.removeFavorite.bind(this)}>Remove</a>
        </div>
      </div>
    );
  }
}

export default connect() (FavoriteRow);