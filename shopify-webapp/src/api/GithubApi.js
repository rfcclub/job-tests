import request from 'superagent';
import * as types from '../constants/ActionTypes';
const PERSONAL_KEY = '9e7584b48e0608057e4bc61d34f5819d15e7e552';
const GITHUB_API_ADDRESS = 'https://api.github.com';
const USER_NAME = 'rfcclub';
const githubService = store => next => action => {
  next(action);
  switch (action.type) {
    case types.SEARCH_REPOSITORY:
      request.get(GITHUB_API_ADDRESS + '/search/repositories')
        .query({
          access_token: PERSONAL_KEY,
          q: action.searchString
        })
        .end((err, res) => {
          processResult(err,
            res,
            next,
            types.SEARCH_REPOSITORY_SUCCESS,
            types.SEARCH_REPOSITORY_FAIL);
        })
      break;  
    case types.ADD_FAVORITE:
      let starrRepository = action.starrRepository;
      request.put(GITHUB_API_ADDRESS + '/user/starred/' + starrRepository)
        .set('Authorization', 'token ' + PERSONAL_KEY)
        .set('Content-Length', 0)
        .end((err, res) => {
          if (err) return next({type: types.ADD_FAVORITE_FAIL, err});
          if (res.statusCode === 204) {
            return next({ type: types.ADD_FAVORITE_OK, starrRepository });
          }
        })
      break;
    case types.REMOVE_FAVORITE:
      let removedRepository = action.starrRepository;
      request.delete(GITHUB_API_ADDRESS + '/user/starred/' + removedRepository)
        .set('Authorization', 'token ' + PERSONAL_KEY)
        .end((err, res) => {
          if (err) return next({type: types.REMOVE_FAVORITE_FAIL, err});
          if (res.statusCode === 204) {
            return next({ type: types.REMOVE_FAVORITE_OK, removedRepository });
          }
        });
      break;
    case types.GET_FAVORITES:
      request.get(GITHUB_API_ADDRESS + '/user/starred')
        .query({access_token: PERSONAL_KEY})
        .end((err, res) => {
          processResult(err,
            res,
            next,
            types.GET_FAVORITES_OK,
            types.GET_FAVORITES_FAIL);
        });
      break;
    case types.GET_TAG:
      request.get(GITHUB_API_ADDRESS + '/repos/' + action.repo + '/tags')
        .set('Authorization', 'token ' + PERSONAL_KEY)
        .end((err, res) => {
          processResult(err,
            res,
            next,
            types.GET_TAG_OK,
            types.GET_TAG_FAIL,
            action.repo);
        });
      break;
    default:
      break;
  }
}
const processResult = (err, res, next, typeOK, typeFail, extra) => {
  if (err) {
    return next({
      type: typeFail,
      err
    })
  }
 
  const data = JSON.parse(res.text)
  next({
    type: typeOK,
    data,
    extra
  })
  
}
export default githubService;