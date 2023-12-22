import React, { useEffect, useState } from 'react';

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

  return (
    <table className='table'>
      <thead>
        <tr>
          {columns.map(c => (
            <th key={c.title} scope='col'>
              {c.title}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {users
          ? users.map(u => (
              <tr key={u.id}>
                <th scope='row'>{u.id}</th>
                {columns.map(c => (
                  <td key={c.title}>{u[c.field]}</td>
                ))}
              </tr>
            ))
          : null}
      </tbody>
    </table>
  );
};

export default ListUsers;
