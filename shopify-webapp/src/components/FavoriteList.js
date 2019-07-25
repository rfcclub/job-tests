import React, { Component } from 'react'
import PropTypes from 'prop-types'
import FavoriteRow from './FavoriteRow';
import { connect } from 'react-redux';
import * as actions from '../actions/AppAction';

class FavoriteList extends Component {
  inFavoriteList(item, favorites) {
    for(var i=0; i<favorites.length; i++) {
      if(item.full_name === favorites[i].full_name) {
        return true;
      }
    }
    return false;
  }
  renderRow() {
    var rows = [];
    if (this.props.favorites && this.props.items) {
      let tags = this.props.tags;
      for (var i=0; i<this.props.items.length;i++) {
        let item = this.props.items[i];
        if (!this.inFavoriteList(item, this.props.favorites)) continue;
        rows.push(<FavoriteRow 
          key={"favorite-row-index-" + i.toString()}
          name={item.full_name} 
          url={item.html_url} 
          language={item.language}
          latestTag={tags[item.full_name] || "-"}
        />)
      }
    }
    return rows;
  }
  render() {
    return (
      <div className="row mt-3">
        <div className="col-10 offset-1">
          <div className="row">
            <div className="col-4 text-left font-weight-bold">Name</div>
            <div className="col-3 text-left font-weight-bold">Language</div>
            <div className="col-3 text-left font-weight-bold">Latest Tag</div>
            <div className="col-2 text-right font-weight-bold">Remove</div>
          </div>
          {this.renderRow()}
        </div>
      </div>
    );
  }
  propTypes = {
    items: PropTypes.array,
    favorites: PropTypes.array,
    tags: PropTypes.object
  }
}

const mapStateToProps = state => ({
  items: state && state.items ? state.items: [],
  favorites: state && state.favorites ? state.favorites: [],
  tags: state && state.tags ? state.tags: new Map(),
  updateFor: state && state.updateFor ? state.updateFor : "all"
})

export default connect(mapStateToProps) (FavoriteList);