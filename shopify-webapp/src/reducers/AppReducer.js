import * as types from '../constants/ActionTypes';

var items = [];
var tags = new Map();
var favorites = [];

function getItem(full_name) {
  for (var i = 0; i < items.length; i++) {
    if (items[i].full_name === full_name) return items[i];
  }
  return null;
}

function inFavorites(item) {
  for (var i = 0; i < favorites.length; i++) {
    if (favorites[i].full_name === item.full_name) return true;
  }
  return false;
}

function findIndexInFavorites(item) {
  for (var i = 0; i < favorites.length; i++) {
    if (favorites[i].full_name === item.full_name) return i;
  }
  return -1;
}
const appReducer = (state = {}, action) => {
  switch (action.type) {
    case types.SEARCH_REPOSITORY_SUCCESS:
      items = action.data.items;
      return {
        items: action.data.items,
        tags: tags,
        favorites: favorites
      };
      break;
    case types.ADD_FAVORITE_OK:
      var item = getItem(action.starrRepository);
      if (!inFavorites(item)) favorites.push(item);
      return { ...state,
        updateFor: 'add_' + action.starrRepository
      };
      break;
    case types.REMOVE_FAVORITE_OK:
      var item = getItem(action.removedRepository);
      let index = findIndexInFavorites(item);
      if (index >= 0) favorites.splice(index, 1);
      return { ...state,
        updateFor: 'remove_' + action.removedRepository
      };
      break;
    case types.CLEAR_RESULT:
      items =[];
      return {
        items: items,
        tags: tags,
        favorites: favorites,
        updateFor: ""
      };
      break;
    case types.GET_TAG_OK:
      if (action.data && action.data.length > 0) {
        tags[action.extra] = action.data[0].name;
        return { ...state,
          updateFor: action.extra
        };
      } else {
        return state;
      }
      break;
    case types.GET_FAVORITES_OK:
      favorites = action.data;
      console.log(favorites);
      return {
        items: items,
        tags: tags,
        favorites: favorites
      }
      break;
    default:
      return state;
  }
}
export default appReducer;