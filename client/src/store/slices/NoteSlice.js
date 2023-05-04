import { createSlice } from '@reduxjs/toolkit';

export const noteSlice = createSlice({
  name: 'note',

  initialState: {
    noteId: '',
    noteTitle: '',
    noteDescription: '',
    noteDate: '',
    noteDashboardId: '',
  },

  reducers: {
    createNote: (state, action) => {
      state.noteId = action.payload.noteId;
      state.noteTitle = action.payload.noteTitle;
      state.noteDescription = action.payload.noteDescription;
      state.noteDate = action.payload.noteDate;
      state.noteDashboardId = action.payload.noteDashboardId;
    },

    deleteNote: (state) => {
      state.noteId = '';
      state.noteTitle = '';
      state.noteDescription = '';
      state.noteDate = '';
      state.noteDashboardId = '';
    },
  },
});

export const { createNote, deleteNote } = noteSlice.actions;
export default noteSlice.reducer;
