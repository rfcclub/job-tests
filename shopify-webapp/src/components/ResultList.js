import React, { Component } from 'react'
import PropTypes from 'prop-types';
import ResultRow from './ResultRow';
import { connect } from 'react-redux';
import * as actions from '../actions/AppAction';
import * as constants from '../constants/ActionTypes';

class ResultList extends Component {
  inFavoriteList(item) {
    for(var i=0; i<this.props.favorites.length; i++) {
      if(item.full_name === this.props.favorites[i].full_name) {
        return true;
      }
    }
    return false;
  }
  renderItems() {
    var rows = [];
    if (this.props.items) {
      let tags = this.props.tags;
      var maxElements = constants.MAX_ROWS <= this.props.items.length ? 
            constants.MAX_ROWS : this.props.items.length;

      for (var i = 0; i<maxElements; i++) {
        let item = this.props.items[i];
        rows.push(<ResultRow 
          key={"result-row-index-" + i.toString()}
          name={item.full_name} 
          url={item.html_url} 
          language={item.language}
          latestTag={tags[item.full_name] || "-"}
          hideAddButton={this.inFavoriteList(item)}
          />)
      }
    }
    return rows;
  }
  render() {
    return (
      <div>
        <div className="row mt-4">
          <div className="col-4 text-left font-weight-bold  mt-4">Name</div>
          <div className="col-4 text-left font-weight-bold mt-4">Language</div>
          <div className="col-3 text-left font-weight-bold mt-4">Latest Tag</div>
          <div className="col-1 text-right font-weight-bold mt-4">Add</div>
        </div>
        {this.renderItems()}
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
  tags: state && state.tags ? state.tags: new Map(),
  favorites: state && state.favorites ? state.favorites: [],
  updateFor: state && state.updateFor ? state.updateFor : "all"
})
export default connect(mapStateToProps)(ResultList);