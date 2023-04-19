import { createSlice } from '@reduxjs/toolkit';

export const dashboardSlice = createSlice({
  name: 'dashboard',

  initialState: {
    dashboardId: '',
    dashboardName: '',
    userId: '',
    allWidgets: [],
  },

  reducers: {
    createDashboard: (state, action) => {
      state.dashboardId = action.payload.dashboardId;
      state.dashboardName = action.payload.dashboardName;
      state.userId = action.payload.userId;
      state.allWidgets = action.payload.allWidgets;
    },

    deleteDashboard: (state) => {
      state.dashboardId = '';
      state.dashboardName = '';
      state.userId = '';
      state.allWidgets = [];
    },
  },
});

export const { createDashboard, deleteDashboard } = dashboardSlice.actions;
export default dashboardSlice.reducer;
