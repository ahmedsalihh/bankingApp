import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const AddUser = () => {
  const navigate = useNavigate();

  const [firstname, setFirstname] = useState('');
  const [lastname, setLastname] = useState('');

  const handleSubmit = e => {
    e.preventDefault();
    const body = {
      firstname,
      lastname,
    };
    addUser(body);
  };

  const addUser = async body => {
    await fetch('http://localhost:8080/users/add', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    });
    navigate(`/`);
  };

  return (
    <div>
      <form>
        <div className='mb-3'>
          <label htmlFor='name' className='form-label'>
            Name
          </label>
          <input
            onChange={e => setFirstname(e.target.value)}
            className='form-control'
            id='firstname'
            value={firstname}
            placeholder='Enter your name'
          />
        </div>
        <div className='mb-3'>
          <label htmlFor='surname' className='form-label'>
            Surname
          </label>
          <input
            onChange={e => setLastname(e.target.value)}
            className='form-control'
            id='lastname'
            value={lastname}
            placeholder='Enter your surname'
          />
        </div>
        <div className='mb-3'>
          <button
            type='submit'
            className='btn btn-primary'
            onClick={handleSubmit}
          >
            Submit
          </button>
        </div>
      </form>
    </div>
  );
};

export default AddUser;
