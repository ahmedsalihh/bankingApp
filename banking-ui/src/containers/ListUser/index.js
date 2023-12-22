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
          <th scope='col'>Actions</th>
        </tr>
      </thead>
      <tbody>
        {users
          ? users.map(u => (
              <tr key={u.id}>
                {columns.map(c => (
                  <td key={c.title}>{u[c.field]}</td>
                ))}
                <td>
                  <button type='button' className='btn btn-primary'>
                    List Credits
                  </button>
                </td>
              </tr>
            ))
          : null}
      </tbody>
    </table>
  );
};

export default ListUsers;
