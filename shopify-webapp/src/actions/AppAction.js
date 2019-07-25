import * as types from '../constants/ActionTypes';

export const clearResult = () => ({
  type: types.CLEAR_RESULT
});
export const searchRepositories = (searchString) => ({
  type: types.SEARCH_REPOSITORY,
  searchString
});
export const addFavorite = (starrRepository) => ({
  type: types.ADD_FAVORITE,
  starrRepository
});
export const removeFavorite = (starrRepository) => ({
  type: types.REMOVE_FAVORITE,
  starrRepository
});

export const getFavorites = () => ({
  type: types.GET_FAVORITES
});

export const getTag =(repo) => ({
  type: types.GET_TAG,
  repo
});