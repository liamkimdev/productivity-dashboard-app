import { configureStore } from '@reduxjs/toolkit';
import authReducer from './slices/AuthSlice.js';
import dashboardReducer from './slices/DashboardSlice.js';
import messagesReducer from './slices/MessagesSlice.js';
import noteReducer from './slices/NoteSlice.js';

export default configureStore({
  reducer: {
    auth: authReducer,
    dashboard: dashboardReducer,
    messages: messagesReducer,
    note: noteReducer,
  },
});
