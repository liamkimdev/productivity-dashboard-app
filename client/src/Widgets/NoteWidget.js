import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import NoteFactory from './NoteFactory';
import '../Styles/Note.css';

function Note() {
  const handlePostNote = (noteData) => {
    fetch('http://localhost:8080/dashboard/noteWidget/note', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(noteData),
    }).then((response) => {
      if (response.status === 200) {
        return response.json();
      } else {
        return Promise.reject(response);
      }
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault(); // prevent form submission
    const noteData = document.getElementById('noteBody').value;
    handlePostNote(noteData);
  };

  // const [wordToAdd, setWordToAdd] = useState({});

  // const [newNote, setNewNote] = useState("");

  // const handleChange = (e) => {
  //   const newWord = { ...wordToAdd };
  //   newWord[e.target.name] = e.target.value;
  //   console.log(newWord);
  //   setWordToAdd(newWord);
  // };

  return (
    <>
      <div className="note">
        <h2>Note Widget</h2>

        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3" controlId="update-note">
            <Form.Label>Post A Note</Form.Label>
            <Form.Control
              as="textarea"
              className="mb-3"
              placeholder="New note"
              id="noteBody"
            />

            <Form.Text className="text-muted mb-3">
              Notes are autosaved!
            </Form.Text>

            <Button
              variant="primary"
              type="submit"
              onClick={() => handleSubmit()}
            >
              Submit
            </Button>
          </Form.Group>
        </Form>

        <NoteFactory />
      </div>
    </>
  );
}

export default Note;
