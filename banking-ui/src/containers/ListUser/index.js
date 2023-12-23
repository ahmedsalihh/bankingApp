import React, { useEffect, useState } from 'react';
import Table from '../../components/Table';
import { useNavigate } from 'react-router-dom';

const ListUsers = () => {
  const navigate = useNavigate();

  const [users, setusers] = useState(null);
  useEffect(() => {
    listUsers();
  }, []);

  const listUsers = async () => {
    const response = await fetch('http://localhost:8080/users/list');
    const users = await response.json();
    setusers(users);
  };

  const handleListCreditsClick = id => {
    navigate(`/listCredits/${id}`);
  };

  const columns = [
    {
      title: 'Id',
      field: 'id',
    },
    {
      title: 'First Name',
      field: 'firstname',
    },
    {
      title: 'Last Name',
      field: 'lastname',
    },
    {
      title: 'Actions',
      field: item => (
        <button
          type='button'
          className='btn btn-primary'
          onClick={() => handleListCreditsClick(item.id)}
        >
          List Credits
        </button>
      ),
    },
  ];

  return <Table columns={columns} data={users} />;
};

export default ListUsers;
