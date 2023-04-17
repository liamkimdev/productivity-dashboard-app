import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
  name: 'auth',

  initialState: {
    user: {},
    authToken: '', // **** only this
    authorities: '',
    //token: 'log in',
  },

  reducers: {
    login: (state, action) => {
      console.log(action.payload);
      state.user = action.payload.user;
      state.authToken = action.payload.jwt_token;
      state.authorities = action.payload.authorities;
    },
    logout: (state) => {
      state.authToken = '';
      state.user = {};
    },

    
  },
});

export const { login, logout, setFormLogInType } = authSlice.actions;
export default authSlice.reducer;
