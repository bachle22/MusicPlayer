<?php
	require "connect.php";
	$query = "SELECT DISTINCT * FROM mv ORDER BY rand(".date("ymd").") LIMIT 3";
	$datamv = mysqli_query($con,$query);
	class Mv{
		function Mv($idmv,$tenmv,$hinhmv,$linkmv){
			$this->IdMv = $idmv;
			$this->TenMv = $tenmv;
			$this->HinhMv = $hinhmv;
			$this->LinkMv = $linkmv;
		}
	}
	$arraymv = array();
	while($row = mysqli_fetch_assoc($datamv)){
		array_push($arraymv,new Mv($row['IdMv'],$row['TenMv'],$row['HinhMv'],$row['LinkMv']));
	}
	echo json_encode($arraymv);
?>