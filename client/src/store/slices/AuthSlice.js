import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
  name: 'auth',

  initialState: {
    authToken: '',
    userId: '',
    username: '',
    authorities: [],
  },

  reducers: {
    login: (state, action) => {
      state.authToken = action.payload.jwt_token;
      state.userId = action.payload.userId;
      state.username = action.payload.username;
      state.authorities = action.payload.authorities;
    },
    logout: (state) => {
      state.authToken = '';
      state.userId = '';
      state.username = '';
      state.authorities = [];
    },
  },
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;
