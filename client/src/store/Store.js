import { configureStore } from '@reduxjs/toolkit';
import authReducer from './slices/AuthSlice.js';

export default configureStore({
  reducer: {
    auth: authReducer,
  },
});
