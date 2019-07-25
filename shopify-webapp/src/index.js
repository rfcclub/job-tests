import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { Provider } from 'react-redux'
import { createStore, applyMiddleware, compose } from 'redux'
import rootReducer from './reducers/AppReducer'
import registerServiceWorker from './registerServiceWorker';
import githubService from './api/GithubApi';
import * as actions from './actions/AppAction';

const store = createStore(rootReducer,{},applyMiddleware(githubService));

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>, 
  document.getElementById('root'));
registerServiceWorker();
