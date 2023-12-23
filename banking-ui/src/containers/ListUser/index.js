import React, { useEffect, useState } from 'react';
import Table from '../../components/Table';

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
    field: () => (
      <button type='button' className='btn btn-primary'>
        List Credits
      </button>
    ),
  },
];

const ListUsers = () => {
  const [users, setusers] = useState(null);
  useEffect(() => {
    listUsers();
  }, []);

  const listUsers = async () => {
    const response = await fetch('http://localhost:8080/users/list');
    const users = await response.json();
    setusers(users);
  };

  return <Table columns={columns} data={users} />;
};

export default ListUsers;
